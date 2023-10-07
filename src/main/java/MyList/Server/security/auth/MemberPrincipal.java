package MyList.Server.security.auth;

import MyList.Server.login.entity.Member;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
@Getter
public class MemberPrincipal extends User implements Serializable {
    private final Member member;
    public MemberPrincipal(Member member) {
        super(member.getUsername(), member.getPassword(), member.getAuthorities()); // member 이름, member의 snsId, member의 권한
        this.member = member; // 인증 받은 멤버를 반환할 수 있음
    }
}

