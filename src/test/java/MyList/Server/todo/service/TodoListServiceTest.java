package MyList.Server.todo.service;

import MyList.Server.todo.entity.TodoList;
import MyList.Server.todo.repository.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoListServiceTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    void testJpa(){
        TodoList todo1 = new TodoList();
        todo1.setContent("밥먹기");
        todo1.setCompleted(Boolean.TRUE);
        this.todoListRepository.save(todo1);

        TodoList todo2 = new TodoList();
        todo2.setContent("스프링 공부하기");
        todo2.setCompleted(Boolean.FALSE);
        this.todoListRepository.save(todo2);
    }

}