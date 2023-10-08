package MyList.Server.list.controller;

import MyList.Server.list.dto.response.TodoListResponseDTO;
import MyList.Server.login.dto.MemberPrincipal;
import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.entity.TodoList;
import MyList.Server.list.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @RequestMapping(value = "/todo/search",method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> searchTodo(@RequestBody TodoListRequestDTO todoListRequestDTO){
        System.out.println("searchTodo = " + todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/save", method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> saveTodo(@RequestBody TodoListRequestDTO todoListRequestDTO) {
        System.out.println("saveTodo = " + todoListRequestDTO);
        todoListService.add(todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/update",method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> updateTodo(@RequestBody TodoListResponseDTO todoListResponseDTO) {
        todoListService.updateById(todoListResponseDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/delete",method = RequestMethod.DELETE)
    public ResponseEntity<List<TodoList>> deleteTodo(@RequestBody TodoListResponseDTO todoListResponseDTO) {
        todoListService.deleteById(todoListResponseDTO.getId());
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
    public ResponseEntity<String> completedTodoList(@RequestParam Long id) {

        todoListService.save_completedTodoList(id);
        return ResponseEntity.ok()
                .body("좋아요를 누른 글을 성공적으로 저장했습니다.");
    }


}
