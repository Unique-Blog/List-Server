//package MyList.Server.security.auth;
//
//import MyList.Server.login.entity.Member;
//import MyList.Server.login.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class PrincipalDetailsService implements UserDetailsService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { // userId로 사용자 호출
//        System.out.println(" 사용자를 찾는 중 입니다. ");
//        Member member = memberRepository.findByUserId(userId);
//        if (member != null) {
//            System.out.println(" 사용자를 찾았습니다. ");
//            return new PrincipalDetails(member);
//        }
//        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
//    }
//}
