package uz.unicorn.deeplearning;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.unicorn.deeplearning.user.UserService;

@EnableJpaAuditing
@OpenAPIDefinition
@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class DeepLearningApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DeepLearningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.create(new UserCreateDTO("998946812030", "Doston Bokhodirov"));
    }
}