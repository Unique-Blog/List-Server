package MyList.Server.login.controller;

import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberService memberService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/hello")
    public String helloController(){
        return "hello";
    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDTO signupRequestDTO){
        String rawPassword = signupRequestDTO.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        signupRequestDTO.setUserPw(encPassword);
        memberService.userSignup(signupRequestDTO);
        return "redirect:/";
    }

    @GetMapping("/home")
    public String MainPage(){
        return "/home";
    }
}
