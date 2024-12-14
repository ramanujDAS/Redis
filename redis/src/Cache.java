import datasource.IDataSource;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Cache<KEY,VALUE>
{
    private  final Map<KEY,VALUE> map ;
    private final IDataSource<KEY ,VALUE> dataSource;
    private final PersistentAlgortithm persistentAlgortithm;
    private static final int THRESHOLD_SIZE = 500;

    public Cache(Map<KEY, VALUE> map, IDataSource<KEY, VALUE> dataSource, PersistentAlgortithm evictionAlgorithm) {
        this.map = map;
        this.dataSource = dataSource;
        this.persistentAlgortithm = evictionAlgorithm;
    }


    public Future<VALUE> get(KEY key){

        if(map.containsKey(key)){
            return CompletableFuture.completedFuture(map.get(key));
        }
        else return dataSource.get(key);

    }

    public Future<Void> set(KEY key , VALUE value) {
        if (!map.containsKey(key) && map.size() >= THRESHOLD_SIZE)
            {
                //LRU LFU
                //update existing
                if (persistentAlgortithm == PersistentAlgortithm.WRITE_THROUGH)
                {
                    return dataSource.persist(key, value)
                            .thenAccept(Void -> map.put(key, value));
                } else {
                    map.put(key, value);
                    dataSource.persist(key, value);
                    return CompletableFuture.completedFuture(null);
                }

            }

        return CompletableFuture.completedFuture(null);
        }

}
