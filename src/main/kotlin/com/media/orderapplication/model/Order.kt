package com.media.orderapplication.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name= "MEDIA_ORDER")
data class Order(
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name= "product_id")
    val productId: String? = null,
    @Column(name= "email")
    val email: String?=null,
    @Column(name= "first_name")
    val firstName: String?=null,
    @Column(name= "last_name")
    val lastName: String?=null
)