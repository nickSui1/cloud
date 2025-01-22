package priv.ns.ecommerce.conf;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Configure security authentication so that other microservices can register
 * Refer to the official Spring Security documentation
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    /** Application context path */
    private final String adminContextPath;
    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();

        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
                //1.Configure the static resources and the login page to be publicly accessible.
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                //2.All other requests must require authentication
                .anyRequest().authenticated()
                .and()
                //3.Configure the login and logout path
                .formLogin().loginPage(adminContextPath+"/login").successHandler(successHandler)
                .and()
                .logout().logoutUrl(adminContextPath+"/logout")
                .and()
                //4.Enable HTTP Basic authentication support, which will be required when registering other service modules.
                .httpBasic()
                .and()
                //5.Enable cookie-based CSRF protection.
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //6.Exclude these paths from CSRF protection to allow other modules to register.
                .ignoringAntMatchers(adminContextPath+"/instances",
                                     adminContextPath+"/actuator/**");
    }
}
