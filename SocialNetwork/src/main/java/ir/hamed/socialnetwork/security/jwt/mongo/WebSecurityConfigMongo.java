package ir.hamed.socialnetwork.security.jwt.mongo;


import ir.hamed.socialnetwork.security.jwt.mongo.jwt.AuthEntryPointJwtMongo;
import ir.hamed.socialnetwork.security.jwt.mongo.jwt.AuthTokenFilterMongo;
import ir.hamed.socialnetwork.security.jwt.mongo.service.UserMongoDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
@ConditionalOnProperty(name = {"mongodb","jwt"})
public class WebSecurityConfigMongo extends WebSecurityConfigurerAdapter {
	@Autowired
	UserMongoDetailsServiceImpl userMongoDetailsService;

	@Autowired
	private AuthEntryPointJwtMongo unauthorizedHandler;

	@Bean
	public AuthTokenFilterMongo authenticationJwtTokenFilter() {
		return new AuthTokenFilterMongo();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userMongoDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
