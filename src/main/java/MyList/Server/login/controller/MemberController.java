package MyList.Server.login.controller;

import MyList.Server.login.dto.LoginDTO;
import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.entity.Member;
import MyList.Server.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @GetMapping({"hello"})
    public String getIndex(){
        String anyContext = "제발 제발 제발";
        return anyContext;
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity<SignupRequestDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        memberService.userSignup(signupRequestDTO);
        return ResponseEntity.ok(signupRequestDTO);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginForm(@RequestBody LoginDTO loginDTO){
        if (memberService.login(loginDTO)){
            return ResponseEntity.ok("로그인 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}
