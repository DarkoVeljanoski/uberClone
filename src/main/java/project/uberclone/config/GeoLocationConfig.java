package project.uberclone.config;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import project.uberclone.exception.geolocation.GeoDataBaseInitializationException;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GeoLocationConfig {
    private static DatabaseReader reader = null;
    private final ResourceLoader resourceLoader;

    @Bean
    public DatabaseReader databaseReader(){
      try {
          log.info("GeoLocationConfig: Trying to load GeoLite2 Database.");
          Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
          InputStream dbAsStream = resource.getInputStream();

          log.info("GeoLocationConfig: database was loaded successfully.");

          return reader = new DatabaseReader.Builder(dbAsStream).fileMode(Reader.FileMode.MEMORY).build();

      } catch (IOException | NullPointerException e) {
          log.error("Database reader couldn't be initialized.", e);
          throw new GeoDataBaseInitializationException();
      }
    }
}
