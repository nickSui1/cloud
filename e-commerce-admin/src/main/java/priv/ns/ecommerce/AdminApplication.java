package priv.ns.ecommerce;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Monitoring Center Server Startup Entry
 *
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(AdminApplication.class,args);
    }
}
