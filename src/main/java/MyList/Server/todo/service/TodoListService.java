package MyList.Server.todo.service;

import MyList.Server.todo.dto.request.TodoListRequestDTO;
import MyList.Server.todo.entity.TodoList;
import MyList.Server.todo.repository.TodoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

        public TodoList add(TodoListRequestDTO todoListRequestDTO){
            TodoList todoList = TodoList.builder()
                    .content(todoListRequestDTO.getContent())
                    .completed(todoListRequestDTO.isCompleted())
                    .userId(todoListRequestDTO.getUserId())
                    .build();
            return this.todoListRepository.save(todoList);
        }

        public  TodoList searchById(Long id){
            return this.todoListRepository.findTodoListById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        public List<TodoList> searchAll(String memberId){
            return this.todoListRepository.findAllByUserId(memberId);
        }
        public TodoList updateById(Long id, TodoListRequestDTO request){
            TodoList todoEntity = this.searchById(id);
            if(request.getContent() != null){
                todoEntity.setContent(request.getContent());
            }
            return this.todoListRepository.save(todoEntity);
        }
        public void deleteById(Long id){
            this.todoListRepository.deleteById(id);
        }
        public void deleteAll(){
            this.todoListRepository.deleteAll();
        }
}

