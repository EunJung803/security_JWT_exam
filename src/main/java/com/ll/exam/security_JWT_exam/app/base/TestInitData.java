package com.ll.exam.security_JWT_exam.app.base;


import com.ll.exam.security_JWT_exam.app.article.service.ArticleService;
import com.ll.exam.security_JWT_exam.app.member.entity.Member;
import com.ll.exam.security_JWT_exam.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner initData(MemberService memberService, PasswordEncoder passwordEncoder, ArticleService articleService) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");

            articleService.write(member1, "제목 1", "내용 1");
            articleService.write(member1, "제목 2", "내용 2");
            articleService.write(member2, "제목 3", "내용 3");
            articleService.write(member2, "제목 4", "내용 4");
        };
    }

}