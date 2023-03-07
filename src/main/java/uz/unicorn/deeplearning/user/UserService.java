package uz.unicorn.deeplearning.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.unicorn.deeplearning.exceptions.BadRequestException;
import uz.unicorn.deeplearning.exceptions.NotFoundException;
import uz.unicorn.deeplearning.user.dto.UserCreateDTO;
import uz.unicorn.deeplearning.user.dto.UserDTO;
import uz.unicorn.deeplearning.utils.MessageKey;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 10:43 PM
 */

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final UserSpecification specification;

    @Override
    public UserDetails loadUserByUsername(String token) {
        return this.getByToken(token);
    }

    public User getByToken(String token) {
        return repository.findByToken(token).orElseThrow(() -> new NotFoundException("user.not.found"));
    }

    public UserDTO get(Long id) {
        return mapper.fromEntity(findById(id));
    }

    public UserDTO create(UserCreateDTO dto) {
        validateByPhone(dto.getPhone());
        User user = mapper.toEntity(dto);
        repository.save(user);
        return mapper.fromEntity(user);
    }

    private void validateByPhone(String phone) {
        if (repository.existsByPhone(phone)) throw new BadRequestException(MessageKey.USER_ALREADY_EXISTS);
    }

    public Page<UserDTO> getAll(Pageable pageable, UserCriteria criteria) {
        return repository.findAll(specification.getSpecification(criteria), pageable).map(mapper::fromEntity);
    }

    private User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user.not.found"));
    }

    /*private void test() {
        ThreadLocalSingleton.getUser();
        new Thread(() -> {
            System.out.println("ThreadLocalSingleton.getUser() = " + ThreadLocalSingleton.getUser());
        }).start();
        ThreadLocalSingleton.getUser();
        throw new BadRequestException("bad.request");
    }*/
}
