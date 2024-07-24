import java.util.concurrent.CompletableFuture;

public class Cache<KEY,VALUE>
{
    public VALUE get(KEY key)
    {
      return null;
    }

    public CompletableFuture<Void> set(KEY key, VALUE value)
    {
       return CompletableFuture.completedFuture(null);
    }
}
