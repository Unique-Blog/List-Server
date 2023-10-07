package MyList.Server.login.dto;

import MyList.Server.login.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String userId;
    private String password;


}
