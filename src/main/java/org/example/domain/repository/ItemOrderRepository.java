package org.example.domain.repository;

import org.example.domain.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
}