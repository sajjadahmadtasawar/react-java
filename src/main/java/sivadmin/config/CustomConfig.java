package sivadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class CustomConfig {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> p.setOneIndexedParameters(false);
    }
}
