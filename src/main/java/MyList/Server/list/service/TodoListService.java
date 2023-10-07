package MyList.Server.list.service;

import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.dto.response.TodoListResponseDTO;
import MyList.Server.list.entity.TodoList;
import MyList.Server.list.repository.TodoListRepository;
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
                    .completed(todoListRequestDTO.getCompleted())
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
        public TodoList updateById(TodoListResponseDTO todoListResponseDTO){
            TodoList todoEntity = this.searchById(todoListResponseDTO.getId());
            if(todoListResponseDTO.getContent() != null){
                todoEntity.setContent(todoListResponseDTO.getContent());
            }
            if(todoListResponseDTO.getCompleted() != null){
                todoEntity.setCompleted(todoListResponseDTO.getCompleted());
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

