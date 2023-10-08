package MyList.Server.list.repository;

import MyList.Server.list.entity.CompletedTodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompletedTodoListRepository extends JpaRepository<CompletedTodoList, Long> {
    Optional<CompletedTodoList> findCompletedTodoListByCreatedAt(LocalDateTime localDateTime);

    List<CompletedTodoList> findAllByUserId(String userId);
}
