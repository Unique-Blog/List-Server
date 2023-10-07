package MyList.Server.todo.dto.response;

import MyList.Server.todo.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponseDTO {
    private String content;
    private boolean completed;

}
