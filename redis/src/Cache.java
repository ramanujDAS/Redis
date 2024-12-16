import datasource.IDataSource;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class Cache<KEY,VALUE>
{
    private  final Map<KEY,Record<VALUE>> map ;
    private final IDataSource<KEY ,VALUE> dataSource;
    private final PersistentAlgortithm persistentAlgortithm;
    private static final int THRESHOLD_SIZE = 500;
    private final EvictionAlgorithm evictionAlgorithm;
    private final Integer maxEvictionTTL;
     

    public Cache( IDataSource<KEY, VALUE> dataSource, PersistentAlgortithm evictionAlgorithm, EvictionAlgorithm evictionAlgorithm1, Integer maxEvictionTTL) {
        this.map = new ConcurrentHashMap<>();
        this.dataSource = dataSource;
        this.persistentAlgortithm = evictionAlgorithm;
        this.evictionAlgorithm = evictionAlgorithm1;
        this.maxEvictionTTL = maxEvictionTTL;
    }


    public Future<VALUE> get(KEY key){

        if (map.containsKey(key)) {
            if (map.get(key).getAccessTimeStamp() >= System.currentTimeMillis() - maxEvictionTTL) {
                return CompletableFuture.completedFuture(map.get(key)).thenApply(Record::getValue);
            }
        } else {
            return dataSource.get(key).thenCompose(value -> set(key, value).thenApply(__ -> value));
        }
      return CompletableFuture.completedFuture(null);
    }


    public CompletableFuture<Void> set(KEY key, VALUE value) {
        if (!map.containsKey(key) && map.size() >= THRESHOLD_SIZE) {
            //LRU LFU
            //update existing
        }

        if (persistentAlgortithm == PersistentAlgortithm.WRITE_THROUGH) {
            return dataSource.persist(key, value)
                    .thenAccept(Void -> map.put(key, value));
        } else {
            map.put(key, value);
            dataSource.persist(key, value);
            return CompletableFuture.completedFuture(null);
        }

    }






}

class Record<VALUE>{
    private final VALUE value;
    private long accessTimeStamp;
    private long accessCount;

    Record(VALUE value) {
        this.value = value;
    }

    public VALUE getValue() {
        return value;
    }

    public long getAccessTimeStamp() {
        return accessTimeStamp;
    }

    public void setAccessTimeStamp(long accessTimeStamp) {
        this.accessTimeStamp = accessTimeStamp;
    }

    public long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }
}
