package azur.support.webtool.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import com.example.tutomvcjpa.spring.security.web.LoggingAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
//    @Autowired
//    private LoggingAccessDeniedHandler accessDeniedHandler;
	
/*    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }*/
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests().antMatchers("/login").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin().loginPage("/login").permitAll();
//	}
	

//    
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers(
//                            "/",
//                            "/js/**",
//                            "/css/**",
//                            "/img/**",
//                            "/webjars/**").permitAll()
//                    .antMatchers("/user/**").hasRole("USER")
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll()
//                .and()
//                .exceptionHandling()
//                    .accessDeniedHandler(accessDeniedHandler);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//            .and()
//                .withUser("manager").password("password").roles("MANAGER");
//    }	
}