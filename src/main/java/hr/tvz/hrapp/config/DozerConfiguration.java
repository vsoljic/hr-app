package hr.tvz.hrapp.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DozerConfiguration {

    private final ApplicationProperties applicationProperties;

    public DozerConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add(applicationProperties.getDozerConfigFilePath());
        mappingFiles.add(applicationProperties.getDozerMappingFilePath());

        dozerBeanMapper.setMappingFiles(mappingFiles);

        return dozerBeanMapper;
    }
}
