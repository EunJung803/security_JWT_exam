package com.ll.exam.security_JWT_exam.app.member.service;

import com.ll.exam.security_JWT_exam.app.AppConfig;
import com.ll.exam.security_JWT_exam.app.member.entity.Member;
import com.ll.exam.security_JWT_exam.app.member.repository.MemberRepository;
import com.ll.exam.security_JWT_exam.app.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public String genAccessToken(Member member) {   // 토큰 생성
        String accessToken = member.getAccessToken();   // 토큰 얻기

        if (StringUtils.hasLength(accessToken) == false ) {
            accessToken = jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60L * 60 * 24 * 365 * 100);    // 100년
            member.setAccessToken(accessToken);
        }

        return accessToken;
    }

    public boolean verifyWithWhiteList(Member member, String token) {
        return member.getAccessToken().equals(token);
    }

    // 내부 호출을 막으려면 사용
    /*
    public Member getByUsername__cached(String username) {
        MemberService thisObj = (MemberService) AppConfig.getContext().getBean("memberService");
        Map<String, Object> memberMap = thisObj.getMemberMapByUsername__cached(username);

        return Member.fromMap(memberMap);
    }
     */

    @Cacheable("member")
    public Map<String, Object> getMemberMapByUsername__cached(String username) {
        Member member = findByUsername(username).orElse(null);
        return member.toMap();
    }
}