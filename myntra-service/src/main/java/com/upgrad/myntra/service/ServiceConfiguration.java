package com.upgrad.myntra.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Enabling the component scan and entity scan of classes in the below mentioned "com.upgrad.myntra.service" and "com.upgrad.myntra.service.entity" packages respectively.
 */
@Configuration
@ComponentScan("com.upgrad.myntra.service")
@EntityScan("com.upgrad.myntra.service.entity")
public class ServiceConfiguration {
}
