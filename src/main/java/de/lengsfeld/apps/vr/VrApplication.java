package de.lengsfeld.apps.vr;

import de.lengsfeld.apps.vr.repository.CemeteryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@EnableCaching
@Slf4j
public class VrApplication {

    private final CemeteryRepository cemeteryRepository;

    @Autowired
    public VrApplication(CemeteryRepository cemeteryRepository) {
        this.cemeteryRepository = cemeteryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(VrApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void run() {
        log.info("Number of Cemeteries: " + cemeteryRepository.count());
    }

    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }
}
