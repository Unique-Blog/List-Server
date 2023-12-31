package MyList.Server.list.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String content;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private Boolean completed;

    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
//    private Member member;

}
