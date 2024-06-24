package dev.s24377.lunar_bookshop.section.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "section")
public class SectionConfig {
    private int defaultNumberOfSubsections;
}
