package uz.unicorn.deeplearning.user;

import org.mapstruct.*;
import uz.unicorn.deeplearning.user.dto.UserCreateDTO;
import uz.unicorn.deeplearning.user.dto.UserDTO;
import uz.unicorn.deeplearning.utils.CommonUtils;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:28 PM
 */

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        imports = {CommonUtils.class}
)
public interface UserMapper {

    UserDTO fromEntity(User user);

    @Mapping(target = "token", expression = "java(CommonUtils.generateUUID())")
    User toEntity(UserCreateDTO dto);
}
