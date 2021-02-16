package com.lee.practice.hellospringdemo;

import com.lee.practice.hellospringdemo.repository.JdbcMemberRepository;
import com.lee.practice.hellospringdemo.repository.JdbcTemplateMemberRepository;
import com.lee.practice.hellospringdemo.repository.MemberRepository;
import com.lee.practice.hellospringdemo.repository.MemoryMemberRepository;
import com.lee.practice.hellospringdemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
