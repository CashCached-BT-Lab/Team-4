package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.RateMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateMatrixRepository extends JpaRepository<RateMatrix, Long> {
    boolean existsByProductId(Long productId);
    List<RateMatrix> findByProductId(Long productId);
    List<RateMatrix> findByProductIdAndCustomerType(Long productId, String customerType);
}
