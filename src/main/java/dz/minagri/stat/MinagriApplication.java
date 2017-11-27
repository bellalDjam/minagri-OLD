/**
 * 
 */
package dz.minagri.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dz.minagri.stat.security.AccountAuthenticationProvider;
import dz.minagri.stat.security.AccountUserDetailsService;

/**
 * @author bellal djamel
 *
 */
@EnableJpaRepositories
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MinagriApplication {

	
	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	public static class SecurityConfiguration extends GlobalMethodSecurityConfiguration {
		
		
		@Bean
		public UserDetailsService userDetailsService(){
	        return new AccountUserDetailsService();
	    }
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
			//@formatter:off
//			auth
//			.inMemoryAuthentication()
//			.withUser("admin").password("p").roles("ADMIN", "USER")
//			.and()
//			.withUser("user").password("p").roles("USER");
			//@formatter:on
		}

		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return authenticationManager();
		}

		static {
			// Use a custom SecurityContextHolderStrategy
			SecurityContextHolder.setStrategyName(VaadinSessionSecurityContextHolderStrategy.class.getName());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MinagriApplication.class, args);
	}
}

