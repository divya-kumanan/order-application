package com.vodafoneziggo.orderapplication.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderResponse(
    val description: String?=null,
    val orderDetails: List<OrderDetails>
){
    companion object{
        fun from(orders: List<Order>): OrderResponse {
            val orderDetails = orders.map { OrderDetails(it.id, it.email, it.firstName, it.lastName, it.productId) }
            return OrderResponse(orderDetails = orderDetails)
        }

        fun from(order: Order): OrderResponse{
            val orderDetails = OrderDetails(order.id, order.email, order.firstName, order.lastName, order.productId)
            return OrderResponse(orderDetails = listOf(orderDetails))
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderDetails(
    @JsonProperty("order_id")
    val orderId: Long? = 0,
    @JsonProperty("email")
    val email: String? = null,
    @JsonProperty("first_name")
    val firstName: String? = null,
    @JsonProperty("last_name")
    val lastName: String? = null,
    @JsonProperty("product_id")
    val productId: String? = null
)