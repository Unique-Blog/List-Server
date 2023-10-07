package MyList.Server.todo.dto.response;


import MyList.Server.todo.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListMainResponseDTO {

    private Long id;
    private String content;
    private boolean completed;

    public TodoListMainResponseDTO(TodoList todoList) {
        this.id = todoList.getId();
        this.content = todoList.getContent();
        this.completed = todoList.getCompleted();
    }
}
