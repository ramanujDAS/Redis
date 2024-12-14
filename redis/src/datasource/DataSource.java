package datasource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public class DataSource<KEY, VALUE> implements IDataSource {

    @Override
    public CompletableFuture<VALUE> get(Object o) {
        KEY key = (KEY) o;
        return null;
    }

    @Override
    public CompletableFuture<Void> persist(Object o, Object o2) {

        KEY key = (KEY) o;

        VALUE value = (VALUE) o2;
        return null;
    }
}
