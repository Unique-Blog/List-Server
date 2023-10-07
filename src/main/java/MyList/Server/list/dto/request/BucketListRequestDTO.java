package MyList.Server.list.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BucketListRequestDTO {
    private String content;
    private boolean completed;
    private String userId;
}
