package co.zw.gotour.server.Configuration;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  // @Bean
  // public EntityManagerFactory entityManagerFactory() {
  //   LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

  //   return null;
  // }

}
