package MyList.Server.list.controller;

import MyList.Server.exception.CustomException;
import MyList.Server.list.dto.response.TodoListResponseDTO;
import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.entity.CompletedPercentage;
import MyList.Server.list.entity.CompletedTodoList;
import MyList.Server.list.entity.TodoList;
import MyList.Server.list.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @RequestMapping(value = "/todo/search",method = RequestMethod.POST)
    public ResponseEntity<CompletedPercentage> searchTodo(@RequestBody TodoListRequestDTO todoListRequestDTO){
        System.out.println("searchTodo = " + todoListRequestDTO);

        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        List<CompletedTodoList> completedTodo = todoListService.searchCompleted(todoListRequestDTO.getUserId());

        double percentage = todoListService.completedPercentage(allListTodo, completedTodo);

        CompletedPercentage completedPercentage = new CompletedPercentage(allListTodo, percentage);

        return ResponseEntity.ok(completedPercentage);
    }

    @RequestMapping(value = "/todo/save", method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> saveTodo(@RequestBody TodoListRequestDTO todoListRequestDTO) {
        if(todoListRequestDTO.getContent() == null || todoListRequestDTO.getContent().trim().isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST, "글이 없습니다.");
        }
        System.out.println("saveTodo = " + todoListRequestDTO);
        todoListService.add(todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/update",method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> updateTodo(@RequestBody TodoListResponseDTO todoListResponseDTO) {
        todoListService.updateTodoList(todoListResponseDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/delete",method = RequestMethod.DELETE)
    public ResponseEntity<List<TodoList>> deleteTodo(@RequestBody TodoListResponseDTO todoListResponseDTO) {
        todoListService.deleteTodoList(todoListResponseDTO.getId());
        List<TodoList> allListTodo = todoListService.searchAll(todoListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/deleteAll",method = RequestMethod.DELETE)
    public ResponseEntity<List<TodoList>> deleteAllTodo(@RequestBody TodoListResponseDTO todoListResponseDTO) {
        todoListService.deleteAll();
        List<TodoList> allListTodo = todoListService.searchAll(todoListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @PostMapping("/todo/completed")
    public ResponseEntity<List<TodoList>> completedTodoList(@RequestParam Long id) {

        TodoList todoList = todoListService.save_completedTodoList(id);
        List<TodoList> allListTodo = todoListService.searchAll(todoList.getUserId());
        return ResponseEntity.ok()
                .body(allListTodo);
    }
}
