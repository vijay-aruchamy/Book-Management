// package com.zerp.bookmanagement.Security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// import com.zerp.bookmanagement.ServiceImpl.UserServiceImpl;

// @Configuration
// @EnableWebMvc
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserServiceImpl userDetailsService;

//     @Autowired
//     private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//     @Autowired
//     private JwtRequestFilter jwtRequestFilter;

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeRequests().antMatchers("/authenticate").permitAll()
//             .anyRequest().authenticated()
//             .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//             .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//         http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
// }
