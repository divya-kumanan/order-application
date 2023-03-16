package com.media.orderapplication.model

data class OrderRequest(
    val productId: String,
    val email: String
)