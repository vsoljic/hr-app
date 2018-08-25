package hr.tvz.hrapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to HR App.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties
public class ApplicationProperties {

    @Value("${dozer.config.file.path}")
    private String dozerConfigFilePath;

    @Value("${dozer.mapping.file.path}")
    private String dozerMappingFilePath;

    public String getDozerConfigFilePath() {
        return dozerConfigFilePath;
    }

    public void setDozerConfigFilePath(String dozerConfigFilePath) {
        this.dozerConfigFilePath = dozerConfigFilePath;
    }

    public String getDozerMappingFilePath() {
        return dozerMappingFilePath;
    }

    public void setDozerMappingFilePath(String dozerMappingFilePath) {
        this.dozerMappingFilePath = dozerMappingFilePath;
    }
}
