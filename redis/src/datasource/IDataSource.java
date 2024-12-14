package datasource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public interface IDataSource<KEY, VALUE> {

    CompletableFuture<VALUE> get(KEY key);

    CompletableFuture<Void> persist(KEY key, VALUE value);

}
