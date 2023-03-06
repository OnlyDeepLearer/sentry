package uz.unicorn.deeplearning.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import uz.unicorn.deeplearning.common.GenericSpecification;
import uz.unicorn.deeplearning.utils.CommonUtils;

import java.util.Optional;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:47 PM
 */

@Component
public class UserSpecification implements GenericSpecification<User, UserCriteria> {

    @Override
    public Specification<User> getSpecification(UserCriteria criteria) {
        return nameSpecification(criteria.getName())
                .and(phoneNumberSpecification(criteria.getPhone()));
    }

    private Specification<User> phoneNumberSpecification(String phone) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(phone)
                .map(i -> criteriaBuilder.like(root.get("phone"), CommonUtils.likeFormat(i)))
                .orElse(null);
    }

    private Specification<User> nameSpecification(String firstName) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(firstName)
                .map(i -> criteriaBuilder.like(root.get("name"), CommonUtils.likeFormat(i)))
                .orElse(null);
    }

}
