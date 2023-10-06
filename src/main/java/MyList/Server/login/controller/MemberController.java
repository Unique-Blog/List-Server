package MyList.Server.login.controller;

import MyList.Server.login.dto.UserDTO;
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
    public String signup(@RequestBody UserDTO userDTO){
        String rawPassword = userDTO.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userDTO.setUserPw(encPassword);
        memberService.signup(userDTO);
        return "redirect:/";
    }
}
