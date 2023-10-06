package MyList.Server.login.dto;


import MyList.Server.login.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private String userName;
    private String userId;
    private String userPw;

    public User of(){
        return User.builder()
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .build();
    }
}
