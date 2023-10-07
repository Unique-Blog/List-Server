package MyList.Server.login.dto;


import MyList.Server.login.entity.Member;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupRequestDTO {

    private String userId;
    private String userPw;

//    public SignupRequestDTO(Member member){
//        this.userId = member.getUserId();
//        this.userName = member.getUsername();
//        this.userPw = member.getUserPw();
//    }
    public Member of(){
        return Member.builder()
                .userId(userId)
                .userPw(userPw)
                .build();
    }
}
