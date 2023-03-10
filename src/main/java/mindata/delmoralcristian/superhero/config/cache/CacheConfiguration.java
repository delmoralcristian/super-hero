package mindata.delmoralcristian.superhero.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.var;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        var cacheManager = new CaffeineCacheManager("superheroes");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(200)
                .maximumSize(500)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats());
        return cacheManager;
    }
}
