package org.cenfo.tareacrud.logic.entity.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // @Query("SELECT o FROM Order o WHERE o.user.id = ?3")
    // List<Order> getOrderByUserId(Long userId);

    // @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    // List<Order> getOrderByUserId(@Param("userId") Long userId);

    Page<Order> getOrderByUserId(Long id, Pageable pageable);
}
