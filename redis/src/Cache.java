import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<KEY,VALUE>
{
    private int maxSize ;
    private Duration expiryTime;
    private ConcurrentHashMap<Object, Object> storage = new ConcurrentHashMap<>();

    public Cache(int maxSize, Duration expiryTime)
    {
        this.maxSize = maxSize;
        this.expiryTime = expiryTime;
    }


    public VALUE get(KEY key)
    {
        if (storage.containsKey(key)) {
            return (VALUE) storage.get(key);
        } else {
            return null;
        }
    }

    public CompletableFuture<Object> set(KEY key, VALUE value)
    {
       return CompletableFuture.completedFuture(
               storage.put(key, value)
       );

    }
}
