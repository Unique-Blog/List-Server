package MyList.Server.login.dto;


import MyList.Server.login.entity.Member;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupRequestDTO {

    private String userName;
    private String userId;
    private String userPw;

    public Member of(){
        return Member.builder()
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .build();
    }
}
