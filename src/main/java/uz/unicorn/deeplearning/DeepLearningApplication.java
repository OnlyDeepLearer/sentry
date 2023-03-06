package uz.unicorn.deeplearning;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.unicorn.deeplearning.user.UserService;
import uz.unicorn.deeplearning.user.dto.UserCreateDTO;

@EnableJpaAuditing
@OpenAPIDefinition
@SpringBootApplication
@RequiredArgsConstructor
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

/**
 * mapper (mapstruct)
 * sentry
 * clean code
 * firebase storage
 * projections
 * jpa specifications
 * aspect(only with throwing)
 * IDEA plugins
 * session with thread local
 */

//@AfterThrowing(value = "execution(* uz.apelsin.ofdcheckservice.ofd.service.PaynetServiceOfdService.*(..)))", throwing = "e")
//public void handleNotFoundException(final NotFoundException e) {
//    Sentry.captureException(e);
//    logService.logException(e);
//}

//@EnableAspectJAutoProxy => for main class
