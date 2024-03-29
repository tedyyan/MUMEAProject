package pl.piomin.services.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableSwagger2
@Configuration
public class AccountApplication extends WebMvcConfigurationSupport{

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public Docket swaggerPersonApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.piomin.services.account.controller"))
                .paths(PathSelectors.any())

                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Account API").description("Documentation Account API v1.0").build());
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
//	@Bean
//	UserRepository repository() {
//		UserRepository repository = new UserRepository();
    // repository.add(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
    // repository.add(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
    // repository.add(new Employee(1L, 1L, "Tom Scott", 26, "Developer"));
    // repository.add(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
    // repository.add(new Employee(1L, 2L, "Patrick Dempsey", 27, "Developer"));
    // repository.add(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
    // repository.add(new Employee(2L, 3L, "Ian Scott", 34, "Developer"));
    // repository.add(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
    // repository.add(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
    // repository.add(new Employee(2L, 4L, "Elisabeth Smith", 30, "Developer"));
//		return repository;
//	}

}
