package com.xnexus.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caelum.stella.validation.CPFValidator;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	
	//Configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario").permitAll()
		.antMatchers("/criarConta").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/produto").permitAll()
		.antMatchers("/produto/**").permitAll()
		.antMatchers("/usuario/username").permitAll()
		.antMatchers("/index").permitAll()
		.antMatchers("/pedidos").permitAll()
		.antMatchers("/compra").permitAll()
		.antMatchers("/detalharProduto/**").permitAll()
		.antMatchers("/detalharProduto").permitAll()
		.antMatchers("/carrinho-rest/**").permitAll()
		.antMatchers("/carrinho").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.formLogin();
	}
	
	//Configurações de recursos estáticos (js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/fonts/**", "/scss/**.scss", "/css/**.css", "/js/**", "/img/**");
	}

}
