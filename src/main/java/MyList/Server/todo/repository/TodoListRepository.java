package MyList.Server.todo.repository;

import MyList.Server.todo.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<TodoList> findTodoListById(Long id);
    List<TodoList> findAllByMember_UserId(String memberId);
}
