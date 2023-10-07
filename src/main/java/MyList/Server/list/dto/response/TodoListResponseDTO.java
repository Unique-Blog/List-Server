package MyList.Server.list.dto.response;

import MyList.Server.list.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponseDTO {
    private Long id;
    private String content;
    private Boolean completed;
    private String userId;

    public TodoListResponseDTO(TodoList todoList) {
        this.id = todoList.getId();
        this.content = todoList.getContent();
        this.completed = todoList.getCompleted();
        this.userId = todoList.getUserId();
    }
}
