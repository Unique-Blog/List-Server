package MyList.Server.list.repository;

import MyList.Server.list.entity.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BucketListRepository extends JpaRepository<BucketList, Long> {
    Optional<BucketList> findBucketListById(Long id);
    List<BucketList> findAllByUserId(String userId);
}
