package MyList.Server.login.controller;

import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/user/signup")
    public String signup(){
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signupProc(@RequestBody SignupRequestDTO signupRequestDTO){
        String rawPassword = signupRequestDTO.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        signupRequestDTO.setUserPw(encPassword);
        memberService.userSignup(signupRequestDTO);
        return "redirect:/login";
    }

    @GetMapping("/user/login")
    public String login(){
        return "loginForm";
    }



}
