package MyList.Server.login.service;

import MyList.Server.login.dto.LoginDTO;
import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.entity.Member;
import MyList.Server.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Transactional
    public Member userSignup(SignupRequestDTO signupRequestDTO){
        Member member = signupRequestDTO.of();
        checkDuplication(signupRequestDTO.of());
        memberRepository.save(member);
        return member;
    }

    private void checkDuplication(Member member) {
        Optional<Member> findMember = memberRepository.findByUserId(member.getUserId());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member login(LoginDTO loginDTO){
        Optional<Member> memberId = memberRepository.findByUserId(loginDTO.getUserId());
        if(memberId.isPresent()){
            Member member = memberId.get();
            if(member.getUserPw().equals(loginDTO.getPassword()))
                return member;
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
