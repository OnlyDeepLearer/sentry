package uz.unicorn.deeplearning.user;

import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import uz.unicorn.deeplearning.user.dto.UserCreateDTO;
import uz.unicorn.deeplearning.user.dto.UserDTO;
import uz.unicorn.deeplearning.utils.CommonUtils;

import java.util.UUID;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:28 PM
 */

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
        imports = {CommonUtils.class}
)
public abstract class UserMapper {


//    protected TokenService tokenService;

//    @Autowired
//    public void setTokenService(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }

    @Mapping(target = "name", ignore = true)
    abstract UserDTO fromEntity(User user);

    @Mapping(target = "token", expression = "java(CommonUtils.generateUUID())")
//    @Mapping(target = "token", constant = "null", expression = "java(tokenService.updateToken(token))")
    abstract User toEntity(UserCreateDTO dto);

    String updateToken(String token) {
        return token + UUID.randomUUID();
    }


}
