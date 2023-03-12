package com.vodafoneziggo.orderapplication.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderResponse(
 val ordersDetails: List<OrderDetails>
){
    companion object{
        fun from(orders: List<Order>): OrderResponse {
            val orderDetails = orders.map { OrderDetails(it.id, it.email, it.firstName, it.lastName, it.productId) }
            return OrderResponse(ordersDetails = orderDetails)
        }
    }
}

data class OrderDetails(
    @JsonProperty("orderID")
    val orderId: Long? = 0,
    @JsonProperty("email")
    val email: String? = null,
    @JsonProperty("first_name")
    val firstName: String? = null,
    @JsonProperty("last_name")
    val lastName: String? = null,
    @JsonProperty("productID")
    val productId: String? = null
)