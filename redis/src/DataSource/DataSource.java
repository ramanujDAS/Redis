package DataSource;

import java.util.HashMap;

public class DataSource<KEY,VALUE>
{
   private  HashMap<KEY,VALUE> storage = new HashMap();

   public VALUE get(KEY key)
   {
       return storage.get(key);
   }
   public void put(KEY key, VALUE value)
   {
       storage.put(key,value);
   }
}
