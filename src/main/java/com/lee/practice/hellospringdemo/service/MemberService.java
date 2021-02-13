package com.lee.practice.hellospringdemo.service;

import com.lee.practice.hellospringdemo.domain.Member;
import com.lee.practice.hellospringdemo.repository.MemberRepository;
import com.lee.practice.hellospringdemo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        /* Optional 타입으로 변수를 선언하면 딱히..보기에 좋지 않기 때문에 리펙토링이 가능하다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/


        // 같은 이름이 있는 중복 회원X
        // 첫번째 리펙토링 한 후

        /* 하지만 이 코드도 결국 findByName 함수를 호출하면 특정 로직이 실행되기 때문에 메소드로 따로 분리하는 것이 좋다.
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });*/

        // 두번째 리펙토링 한 후
        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }




}
