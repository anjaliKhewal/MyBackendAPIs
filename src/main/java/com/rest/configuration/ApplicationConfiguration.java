/**
 * 
 */
package com.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Anjali
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.rest.resource")
public class ApplicationConfiguration {

}
