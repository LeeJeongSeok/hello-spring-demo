package com.lee.practice.hellospringdemo.repository;

import com.lee.practice.hellospringdemo.domain.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트의 순서는 보장되진 않는다. 일괄적으로 실행할 경우 findAll, findByName에서 에러가 나기 때문에 테스트 구간이 끝날 때 마다 repository를 비워준다.
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();


        assertEquals(member, result);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");

        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertEquals(member1, result);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");

        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(result.size(), 2);
    }

}
