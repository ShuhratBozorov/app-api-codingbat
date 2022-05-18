package uz.pdp.appapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    @NotNull(message = "languageName bo'sh bo'lmasligi kerak!")
    public String name;
}
