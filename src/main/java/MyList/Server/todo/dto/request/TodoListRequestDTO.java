package MyList.Server.todo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListRequestDTO {
    private String content;
    private boolean completed;
    private String userId;
}
