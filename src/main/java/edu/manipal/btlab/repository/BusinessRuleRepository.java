package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.BusinessRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRuleRepository extends JpaRepository<BusinessRule, Long> {
    boolean existsByProductId(Long productId);
    List<BusinessRule> findByProductId(Long productId);
}
