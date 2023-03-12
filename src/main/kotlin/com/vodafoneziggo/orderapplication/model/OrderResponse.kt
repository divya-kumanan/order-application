package com.vodafoneziggo.orderapplication.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

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
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
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