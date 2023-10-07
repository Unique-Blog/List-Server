package MyList.Server.list.dto.response;

import MyList.Server.list.entity.BucketList;
import MyList.Server.list.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BucketListResponseDTO {
    private Long id;
    private String content;
    private Boolean completed;
    private String userId;

    public BucketListResponseDTO(BucketList bucketList) {
        this.id = bucketList.getId();
        this.content = bucketList.getContent();
        this.completed = bucketList.getCompleted();
        this.userId = bucketList.getUserId();
    }
}