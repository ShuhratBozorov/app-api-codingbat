package uz.pdp.appapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotNull(message = "taskName bo'sh bo'lmasligi kerak!")
    private String name;
    @NotNull(message = "text bo'sh bo'lmasligi kerak!")
    private String text;
    private String solution;
    private String method;
    private boolean has_star;
    private Integer languageId;
}
