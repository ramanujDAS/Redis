package dataSource;

import java.util.concurrent.CompletionStage;

public interface DataSource<KEY, VALUE> {

    CompletionStage<VALUE> get(KEY key);

    CompletionStage<Void> persist(KEY key, VALUE value, long timeStamp);

}
