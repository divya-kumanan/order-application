package com.media.orderapplication.repository

import com.media.orderapplication.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun existsByProductIdAndEmail(productId: String, email: String): Boolean
    fun findByEmail(email: String): List<Order>
}