package com.lee.practice.hellospringdemo;

import com.lee.practice.hellospringdemo.repository.MemberRepository;
import com.lee.practice.hellospringdemo.repository.MemoryMemberRepository;
import com.lee.practice.hellospringdemo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
