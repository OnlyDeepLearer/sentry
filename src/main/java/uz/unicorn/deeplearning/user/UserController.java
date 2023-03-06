package uz.unicorn.deeplearning.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.unicorn.deeplearning.response.ResponseEntity;
import uz.unicorn.deeplearning.user.dto.UserCreateDTO;
import uz.unicorn.deeplearning.user.dto.UserDTO;

import javax.validation.Valid;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:19 PM
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/all")
    public ResponseEntity<Page<UserDTO>> getAll(
            @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody UserCriteria criteria
    ) {
        return ResponseEntity.ok(service.getAll(pageable, criteria));
    }
}
