package priv.ns.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import priv.ns.ecommerce.service.AuditorAwareService;
import priv.ns.ecommerce.service.impl.AuditorAwareImpl;

@Configuration
public class AuditorConfig {
    private final AuditorAwareService auditorAwareService;
    public AuditorConfig(AuditorAwareService auditorAwareService) {
        this.auditorAwareService = auditorAwareService;
    }

    @Bean
    public AuditorAware<String> getAuditorAwareService(AuditorAwareService auditorAwareService) {
        return new AuditorAwareImpl(auditorAwareService);
    }
}
