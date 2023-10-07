package MyList.Server.login.service;

import MyList.Server.login.dto.LoginDTO;
import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.entity.Member;
import MyList.Server.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public Member userSignup(SignupRequestDTO signupRequestDTO){
        checkDuplication(signupRequestDTO.of());
        Member member = signupRequestDTO.of();
        System.out.println("회원가입");
        System.out.println("member = " + member);
        memberRepository.save(member);
        return member;
    }

    private void checkDuplication(Member member) {
        Optional<Member> findMember = memberRepository.findByUserId(member.getUserId());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public boolean login(LoginDTO loginDTO){
        Optional<Member> memberId = memberRepository.findByUserId(loginDTO.getUserId());
        if(memberId.isPresent()){
            Member member = memberId.get();
            return member.getUserPw().equals(loginDTO.getPassword());
        }
        return false;
    }


}
