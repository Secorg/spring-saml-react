package com.secorg.spring.samlreact.config;


import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

import com.secorg.spring.samlreact.services.SAMLUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

/**
 * {@link SecurityConfiguration} handles security configuration for the application.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${security.saml2.metadata-url}")
  String metadataUrl;

  @Value("${server.ssl.key-alias}")
  String keyAlias;

  @Value("${server.ssl.key-store-password}")
  String password;

  @Value("${server.port}")
  String port;

  @Value("${server.ssl.key-store}")
  String keyStoreFilePath;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    //Configure SAML Identity provider & Spring app Service Provider
    //Uses configuration from application.properties to configure the
    //The service provider and identity provider.
    //Certain url patterns are allowed to go through without authentication.
    http
        .authorizeRequests()
        .antMatchers("/saml*").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/assets/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .apply(saml())
        .userDetailsService(samlUserDetailsService())
        .serviceProvider()
        .keyStore()
        .storeFilePath(this.keyStoreFilePath)
        .password(this.password)
        .keyname(this.keyAlias)
        .keyPassword(this.password)
        .and()
        .protocol("https")
        .hostname(String.format("%s:%s", "localhost", this.port))
        .basePath("/")
        .and()
        .identityProvider()
        .metadataFilePath(this.metadataUrl);
  }

  /**
   * Configures {@link SAMLUserDetailsService} service implementation .
   * @return {@link SAMLUserDetailsServiceImpl} service implementation instance.
   */
  @Bean
  public SAMLUserDetailsService samlUserDetailsService(){
    return new SAMLUserDetailsServiceImpl();
  }
}