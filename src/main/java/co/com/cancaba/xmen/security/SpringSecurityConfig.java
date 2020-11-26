package co.com.cancaba.xmen.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configuracion para autenticacion por cabecera.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Endpoints que no requieren autenticacion.
     */
    public static final String[] ENDPOINTS_NO_AUTH = new String[] {"/v2/api-docs", "/v2/api-docs/",
            "/swagger-resources/**/", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
            "/cancaba/xmen/api/v1/mutant"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Entry points
        http.authorizeRequests().antMatchers(ENDPOINTS_NO_AUTH).permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
