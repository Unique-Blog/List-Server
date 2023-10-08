package MyList.Server.list.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BucketList {

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
}
