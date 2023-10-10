package MyList.Server.login.controller;

import MyList.Server.login.dto.LoginDTO;
import MyList.Server.login.dto.SignupRequestDTO;
import MyList.Server.login.entity.Member;
import MyList.Server.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @GetMapping({"hello"})
    public String getIndex(){;
        return "hello";
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity<SignupRequestDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        memberService.userSignup(signupRequestDTO);
        return ResponseEntity.ok(signupRequestDTO);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<Member> loginForm(@RequestBody LoginDTO loginDTO){
        System.out.println("loginDTO = " + loginDTO);
        Member member = memberService.login(loginDTO);
        if (member != null){
            return ResponseEntity.ok(member);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }
}
