package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.ProductTermProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTermProfileRepository extends JpaRepository<ProductTermProfile, Long> {
    List<ProductTermProfile> findByProductId(Long productId);
}
