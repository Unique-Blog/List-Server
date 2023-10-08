package MyList.Server.list.repository;

import MyList.Server.list.entity.CompletedBucketList;
import MyList.Server.list.entity.CompletedTodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedBucketListRepository extends JpaRepository<CompletedBucketList, Long> {
    Optional<CompletedBucketList> findCompletedBucketListById(Long id);

    List<CompletedBucketList> findAllByUserId(String userId);
}