package uz.pdp.appapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {
    @NotNull(message = "text bo'sh bo'lmasligi kerak!")
    private String text;

    private Integer task_id;
}
