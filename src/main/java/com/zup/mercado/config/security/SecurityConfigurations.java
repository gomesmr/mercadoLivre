package com.zup.mercado.config.security;



import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticationService autenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;


	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configurações de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.antMatchers(HttpMethod.POST, "/actuator/**").permitAll()
				.antMatchers(HttpMethod.POST, "/perfis").authenticated()
				.antMatchers(HttpMethod.POST, "/categorias/**").authenticated()
				.antMatchers(HttpMethod.POST, "/produtos/**").authenticated()
				.antMatchers(HttpMethod.POST, "/**/pagseguro").authenticated()
				.antMatchers(HttpMethod.POST, "/notas-fiscais").permitAll()
				.antMatchers(HttpMethod.POST, "/ranking").permitAll()
				.antMatchers(HttpMethod.POST, "/concedeperfil").hasAnyAuthority("TEST")
				.anyRequest().authenticated()
		.and()
				.cors()
		.and()
				.csrf().disable()
		.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
				.addFilterBefore(new AutenticationTokenFilter(tokenService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class)

		;
	}
	
	
	//Configurações de recursos estáticos (js, css, images, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}
