package sivadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import sivadmin.config.AppIntializer;
import sivadmin.models.Bruker;
import sivadmin.repository.BrukerRepository;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        SivadminApplication.class,
        Jsr310JpaConverters.class
})

public class SivadminApplication extends AppIntializer {

    public static void main(String[] args) {
        SpringApplication.run(SivadminApplication.class, args);
    }

}