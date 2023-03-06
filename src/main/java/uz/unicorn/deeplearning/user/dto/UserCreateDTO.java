package uz.unicorn.deeplearning.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.deeplearning.utils.MessageKey;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:35 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDTO {

    @NotBlank(message = MessageKey.INVALID_PHONE)
    @Pattern(regexp = "\\d", message = MessageKey.INVALID_PHONE)
    private String phone;

    @Size(min = 3, max = 30)
    @NotBlank(message = MessageKey.INVALID_NAME)
    private String name;
}
