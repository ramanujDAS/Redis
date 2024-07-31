import java.time.Duration;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Cache<String, String> cache = new Cache<>(10, Duration.ofSeconds(10));
        System.out.println(cache.set("Ram", "Bhagalpur"));
        System.out.println(cache.get("Ram"));
        Random random = new Random();
     for(int i =0 ; i < 100000 ;i++){
         String key = String.valueOf(random.nextInt(100000));
         String value = String.valueOf(random.nextInt(100000));
         System.out.println(key +" "+ value);
        cache.set(key, value);
         System.out.println(cache.get(key));
     }
    }
}