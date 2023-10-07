package MyList.Server.login.controller;

import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.entity.Member;
import MyList.Server.login.service.MemberService;
import MyList.Server.security.auth.PrincipalDetails;
import MyList.Server.security.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @GetMapping({"", "/"})
    public String getIndex(){
        String anyContext = "제발 제발 제발";
        return anyContext;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        String rawPassword = signupRequestDTO.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        signupRequestDTO.setUserPw(encPassword);
        memberService.userSignup(signupRequestDTO);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> loginForm(@RequestBody Member member){
        principalDetailsService.loadUserByUsername(member.getUserId());
        return ResponseEntity.ok("로그인성공");
    }
}
