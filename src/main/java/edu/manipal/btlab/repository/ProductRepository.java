package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductCode(String productCode);
    List<Product> findByStatus(String status);
}
