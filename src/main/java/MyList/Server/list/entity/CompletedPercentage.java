package MyList.Server.list.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletedPercentage {
    private List<?> allList;
    private double percentage;
}
