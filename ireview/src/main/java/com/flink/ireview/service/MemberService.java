package com.flink.ireview.service;

import com.flink.ireview.model.entity.UserInfo;
import com.flink.ireview.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {



    private MemberRepository memberRepository;

    @Transactional
    public UserInfo joinUser(UserInfo memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserInfo memberTest = memberRepository.findByAccount(username);
        UserInfo memberTest = memberRepository.findByEmail(username);
        System.out.println(username);
        System.out.println("----------------");
        //System.out.println(memberTest.getEmail());

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("test@test.com").equals(username)) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        } else {
            authorities.add(new SimpleGrantedAuthority("user"));
            System.out.println("user!");
        }
        User user= new User(memberTest.getNickName(), memberTest.getPassword(), authorities);
        return user;
    }

    public String getUserName(){
        return null;
    }
}