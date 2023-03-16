package com.media.orderapplication.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(val data: List<User>?)

data class User(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("email")
    val email: String?,
    @JsonProperty("first_name")
    val firstName: String?,
    @JsonProperty("last_name")
    val lastName: String?
)