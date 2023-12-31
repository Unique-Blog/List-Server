package MyList.Server.list.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListRequestDTO {
    private String content;
    private Boolean completed;
    private String userId;
}
