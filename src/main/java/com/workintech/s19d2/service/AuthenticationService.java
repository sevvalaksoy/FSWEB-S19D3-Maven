package com.workintech.s19d2.service;

import com.workintech.s19d2.exception.AccountException;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(MemberRepository memberRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password){
        if(memberRepository.findByEmail(email).isPresent()){
            throw new AccountException("User with given email already exist", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Role memberRole = roleRepository.findByAuthority("ADMIN").get();

        List<Role> roles = new ArrayList<>();
        roles.add(memberRole);

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles(roles);

        return memberRepository.save(member);
    }
}
