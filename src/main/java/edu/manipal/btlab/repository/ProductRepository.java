package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
