package com.w2m.zeraus.supher.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "com.w2m.zeraus.supher" })
@EntityScan(basePackages = { "com.w2m.zeraus.supher.entity" })
@EnableJpaRepositories(basePackages = { "com.w2m.zeraus.supher.persistence" })
public class SuperherosApplicationConfig {

}
