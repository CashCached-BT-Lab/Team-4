package edu.manipal.btlab.repository;

import edu.manipal.btlab.entity.ProductAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAuditLogRepository extends JpaRepository<ProductAuditLog, Long> {
}
