package MyList.Server.list.service;

import MyList.Server.exception.CustomException;
import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.dto.response.TodoListResponseDTO;
import MyList.Server.list.entity.CompletedTodoList;
import MyList.Server.list.entity.TodoList;
import MyList.Server.list.repository.CompletedTodoListRepository;
import MyList.Server.list.repository.TodoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final CompletedTodoListRepository completedTodoListRepository;

    @Transactional
    public TodoList add(TodoListRequestDTO todoListRequestDTO){
        TodoList todoList = TodoList.builder()
                .content(todoListRequestDTO.getContent())
                .completed(todoListRequestDTO.getCompleted())
                .createdAt(LocalDateTime.now())
                .userId(todoListRequestDTO.getUserId())
                .build();
        return this.todoListRepository.save(todoList);
    }

    @Transactional
    public TodoList save_completedTodoList(Long id) {
        TodoList todoList = todoListRepository.findTodoListById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "id값에 맞는 TodoList가 존재하지 않습니다."));

        if (delete_completedTodoList(todoList.getCreatedAt(),todoList)) {// scrap을 한번 더 누르면 DB에 존재하는지 확인한 뒤, 삭제하고 return false

            CompletedTodoList completedTodoList = CompletedTodoList.builder() // scrapSummaryCodeRepository에 저장
                    .content(todoList.getContent())
                    .createdAt(todoList.getCreatedAt())
                    .userId(todoList.getUserId())
                    .build();
            completedTodoListRepository.save(completedTodoList);

            todoList.setCompleted(true); // summaryCode 객체에서 스크랩 여부를 YES로 변경
            todoListRepository.save(todoList);
            return todoList;
        }
        return todoList;
    }

    private boolean delete_completedTodoList(LocalDateTime localDateTime, TodoList todoList) {
        // localDateTime은 밀리초까지 나옴. 그래서 동일한 값이 없다고 판단 하에 검증 필드로 사용
        Optional<CompletedTodoList> completedTodoList = completedTodoListRepository.findCompletedTodoListByCreatedAt(localDateTime);
        if (completedTodoList.isPresent()) {
            completedTodoListRepository.delete(completedTodoList.get());

            todoList.setCompleted(false); // summaryCode 객체에서 스크랩 여부를 NO로 변경
            todoListRepository.save(todoList);
            return false;
        } else {
            return true;
        }
    }

    public  TodoList searchTodoList(Long id) {
        return this.todoListRepository.findTodoListById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<TodoList> searchAll(String memberId){
        return this.todoListRepository.findAllByUserId(memberId);
    }

    public List<CompletedTodoList> searchCompleted(String memberId){
        return this.completedTodoListRepository.findAllByUserId(memberId);
    }

    public TodoList updateTodoList(TodoListResponseDTO todoListResponseDTO){
        TodoList todoEntity = this.searchTodoList(todoListResponseDTO.getId());
        if(todoListResponseDTO.getContent() != null){
            todoEntity.setContent(todoListResponseDTO.getContent());
        }
        return this.todoListRepository.save(todoEntity);
    }

    @Transactional
    public void deleteTodoList(Long id){
        TodoList todoList = todoListRepository.findTodoListById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "id값에 맞는 TodoList가 존재하지 않습니다."));
        this.todoListRepository.deleteTodoListByCreatedAt(todoList.getCreatedAt());
        this.completedTodoListRepository.deleteTodoListByCreatedAt(todoList.getCreatedAt());

    }
    public void deleteAll(){
        this.todoListRepository.deleteAll();
        this.completedTodoListRepository.deleteAll();
    }

    public double completedPercentage(List<TodoList> allList, List<CompletedTodoList> completedList) {
        int totalSize = allList.size();
        int completedSize = completedList.size();

        double completionPercentage = (double) completedSize / totalSize * 100.0;

        System.out.println("completionPercentage = " + completionPercentage);

        return completionPercentage;
    }
}

