package uz.unicorn.deeplearning.common;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:50 PM
 */

public interface GenericSpecification<T extends BaseEntity, C> {

    Specification<T> getSpecification(C criteria);

}
