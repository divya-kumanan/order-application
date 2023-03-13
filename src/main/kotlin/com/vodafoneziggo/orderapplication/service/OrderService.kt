package com.vodafoneziggo.orderapplication.service

import com.vodafoneziggo.orderapplication.OrderAlreadyExistsException
import com.vodafoneziggo.orderapplication.UserNotFoundException
import com.vodafoneziggo.orderapplication.model.Order
import com.vodafoneziggo.orderapplication.model.OrderRequest
import com.vodafoneziggo.orderapplication.model.UserResponse
import com.vodafoneziggo.orderapplication.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userService: UserService
) {

    fun createOrder(orderRequest: OrderRequest): Long {
        //Retrieve User Details from User Api
        val userResponse = userService.retrieveUserResponse()

        val email = orderRequest.email
        val productId = orderRequest.productId
        val userDetails = getUserDetails(userResponse, email)

        // Check if the email exists in UserResponse
        if (userDetails?.isEmpty() == true) {
            throw UserNotFoundException("Unable to find the user account associated with this email $email")
        }

        // Check if the customer has not ordered this product already
        if (orderRepository.existsByProductIdAndEmail(productId, email)) {
            throw OrderAlreadyExistsException("Product $productId has been already ordered")
        }

        // Save the order to the database
        val order = Order(
            productId = productId,
            email = email,
            firstName = userDetails?.first()?.firstName,
            lastName =  userDetails?.first()?.lastName
        )
        val savedOrder = orderRepository.save(order)

        return savedOrder.id!!
    }

    private fun getUserDetails(userResponse: UserResponse?, email: String) =
        userResponse?.data?.filter { it.email == email }

    // Get all orders from the database associated with the email account
    fun getOrdersByEmail(email: String): List<Order>? = orderRepository.findByEmail(email)

    // Get order details based on id
    fun getOrderByOrderId(id: Long) = orderRepository.findById(id)

    // Get all orders from the database
    fun getAllOrders(): List<Order> = orderRepository.findAll()
}