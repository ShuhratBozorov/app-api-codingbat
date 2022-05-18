package uz.pdp.appapicodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "email bo'sh bo'lmasligi kerak!")
    public String email;
    @NotNull(message = "pssword bo'sh bo'lmasligi kerak!")
    public String password;
}
