package MyList.Server.list.repository;

import MyList.Server.list.entity.CompletedBucketList;
import MyList.Server.list.entity.CompletedTodoList;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompletedBucketListRepository extends JpaRepository<CompletedBucketList, Long> {
    Optional<CompletedBucketList> findCompletedBucketListByCreatedAt(LocalDateTime localDateTime);

    List<CompletedBucketList> findAllByUserId(String userId);
}