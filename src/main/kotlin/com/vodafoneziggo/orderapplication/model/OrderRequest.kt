package com.vodafoneziggo.orderapplication.model

data class OrderRequest(
    val productId: String,
    val email: String
)