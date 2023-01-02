package mindata.delmoralcristian.superhero.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "swagger.service")
public class SwaggerProperties {

    private String version;
    private String title;
    private String description;
    private String termsPath;
    private String email;
    private String licenceType;
    private String licencePath;

}
