package mindata.delmoralcristian.superhero.config.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@Configuration
public class SwaggerConfiguration {

    private static final String HTTP_PROTOCOL = "http";
    private static final String HTTPS_PROTOCOL = "https";
    private static final String MICROSERVICE_PATH = "/api";

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .pathMapping("/")
                .protocols(Sets.newHashSet(HTTP_PROTOCOL, HTTPS_PROTOCOL))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getTermsPath())
                .contact(new Contact(StringUtils.EMPTY, StringUtils.EMPTY, swaggerProperties.getEmail()))
                .license(swaggerProperties.getLicenceType())
                .licenseUrl(swaggerProperties.getLicencePath())
                .version(swaggerProperties.getVersion())
                .build();
    }

    /**
     * @return the predicate used to check if the path should be included or not.
     */
    private Predicate<String> paths() {
        return or(regex(new StringBuilder(MICROSERVICE_PATH).append(".*").toString()));
    }
}

