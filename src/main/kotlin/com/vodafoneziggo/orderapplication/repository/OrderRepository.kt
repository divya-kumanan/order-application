package com.vodafoneziggo.orderapplication.repository

import com.vodafoneziggo.orderapplication.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun existsByProductIdAndEmail(productId: String, email: String): Boolean
    fun findByEmail(email: String): List<Order>
}