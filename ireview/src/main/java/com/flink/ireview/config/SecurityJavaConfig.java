package com.flink.ireview.config;

import com.flink.ireview.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService2;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .formLogin().disable();
//        http.authorizeRequests()
//                // 페이지 권한
//                .antMatchers("/**").permitAll()
//                // 로그인
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                // 로그아웃
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true);
//        http.authorizeRequests()
//                .antMatchers("**")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/login/success")
//                .permitAll();
        // 일단 보류...
        //csrf 가 disable 해야지 통신이 가능함  5.11

        http.authorizeRequests()
//                .antMatchers("/api/member/login").permitAll()
//                .antMatchers("/api/member/signUp").permitAll()
//                .antMatchers("/api/member/myInfo").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/api/member/login")
                .defaultSuccessUrl("/api/member/login/success")
                .failureUrl("/api/member/login/failure")
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/member/logout"))
                .logoutSuccessUrl("/api/member/logout/result")
                .invalidateHttpSession(true);

    }
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
        // 스프링 시큐리티 로그인 기능 구현하기 configure
       auth.userDetailsService(memberService2).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
