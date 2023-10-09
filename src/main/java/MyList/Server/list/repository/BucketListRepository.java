package MyList.Server.list.repository;

import MyList.Server.list.entity.BucketList;
import MyList.Server.list.entity.CompletedBucketList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BucketListRepository extends JpaRepository<BucketList, Long> {
    Optional<BucketList> findBucketListById(Long id);

    Optional<BucketList> deleteBucketListByCreatedAt(LocalDateTime localDateTime);
    List<BucketList> findAllByUserId(String userId);
}
