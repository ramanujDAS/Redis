import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Cache<KEY,VALUE>
{
    private int maxSize ;
    private Duration expiryTime;

    public Cache(int maxSize, Duration expiryTime)
    {
        this.maxSize = maxSize;
        this.expiryTime = expiryTime;
    }


    public VALUE get(KEY key)
    {
      return null;
    }

    public CompletableFuture<Void> set(KEY key, VALUE value)
    {
       return CompletableFuture.completedFuture(null);

    }
}
