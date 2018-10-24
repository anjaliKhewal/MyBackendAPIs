/**
 * 
 */
package com.myapp.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Anjali
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.myapp")
public class ApplicationConfiguration {

}
