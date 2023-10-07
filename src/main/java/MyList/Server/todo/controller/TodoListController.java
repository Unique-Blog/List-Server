package MyList.Server.todo.controller;

import MyList.Server.login.dto.MemberPrincipal;
import MyList.Server.login.entity.Member;
import MyList.Server.todo.dto.request.TodoListRequestDTO;
import MyList.Server.todo.entity.TodoList;
import MyList.Server.todo.service.TodoListService;
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
        System.out.println("todoListRequestDTO = " + todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/save", method = RequestMethod.POST)
    public ResponseEntity<List<TodoList>> saveTodo(@RequestBody TodoListRequestDTO todoListRequestDTO) {
        System.out.println("todoListRequestDTO = " + todoListRequestDTO);
        todoListService.add(todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(todoListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/update",method = RequestMethod.PATCH)
    public ResponseEntity<List<TodoList>> updateTodo(@RequestBody Long id, TodoListRequestDTO todoListRequestDTO,
                                                     @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        todoListService.updateById(id, todoListRequestDTO);
        List<TodoList> allListTodo = todoListService.searchAll(memberPrincipal.getMember().getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/delete",method = RequestMethod.DELETE)
    public ResponseEntity<List<TodoList>> deleteTodo(@RequestBody Long id,
                                                     @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        todoListService.deleteById(id);
        List<TodoList> allListTodo = todoListService.searchAll(memberPrincipal.getMember().getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/todo/deleteAll",method = RequestMethod.DELETE)
    public ResponseEntity<List<TodoList>> deleteAllTodo(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        todoListService.deleteAll();
        List<TodoList> allListTodo = todoListService.searchAll(memberPrincipal.getMember().getUserId());
        return ResponseEntity.ok(allListTodo);
    }


}
