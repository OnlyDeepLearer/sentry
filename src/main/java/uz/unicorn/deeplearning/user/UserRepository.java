package uz.unicorn.deeplearning.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 10:43 PM
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByToken(String token);

    boolean existsByPhone(String phone);

//    @Query(value = "select id, name, blocked from users where phone like :phone")
//    List<SampleProjection> getAll(String phone "%%" );
}
