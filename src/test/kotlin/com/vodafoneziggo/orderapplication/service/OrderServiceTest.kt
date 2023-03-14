package com.vodafoneziggo.orderapplication.service

import com.vodafoneziggo.orderapplication.exception.OrderAlreadyExistsException
import com.vodafoneziggo.orderapplication.exception.UserNotFoundException
import com.vodafoneziggo.orderapplication.model.Order
import com.vodafoneziggo.orderapplication.model.OrderRequest
import com.vodafoneziggo.orderapplication.model.User
import com.vodafoneziggo.orderapplication.model.UserResponse
import com.vodafoneziggo.orderapplication.repository.OrderRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class OrderServiceTest {

    @Mock
    private lateinit var orderRepository: OrderRepository

    @Mock
    private lateinit var userService: UserService

    @InjectMocks
    private lateinit var orderService: OrderService

    @Test
    fun `should create order successfully`() {
        // given
        val orderRequest = OrderRequest(productId = "prd1234", email = "abc@def.com")
        val order = Order(id= null, email = "abc@def.com", productId = "prd1234",  firstName = "George", lastName = "Bluth")
        val user = User(id=1, firstName = "George", lastName = "Bluth", email =  "abc@def.com")

        `when`(userService.retrieveUserResponse()).thenReturn(UserResponse(data = listOf(user)))
        `when`(orderRepository.existsByProductIdAndEmail("prd1234", "abc@def.com")).thenReturn(false)
        `when`(orderRepository.save(order)).thenReturn(order.copy(id=1L))

        // when
        val orderId = orderService.createOrder(orderRequest)

        // then
        assertNotNull(orderId)
        verify(orderRepository, times(1)).existsByProductIdAndEmail("prd1234", "abc@def.com")
        verify(orderRepository, times(1)).save(any())
    }

   @Test
    fun `should throw exception when email does not exist`() {
       // given
       val orderRequest = OrderRequest(productId = "prd1234", email = "abc@def.com")
       val user = User(id=1, firstName = "George", lastName = "Bluth", email =  "def@def.com")

       `when`(userService.retrieveUserResponse()).thenReturn(UserResponse(data = listOf(user)))

       // when and then
       assertThrows(UserNotFoundException::class.java) {
           orderService.createOrder(orderRequest)
       }
       verify(orderRepository, never()).existsByProductIdAndEmail("prd1234", "abc@def.com")
       verify(orderRepository, never()).save(any())
    }

    @Test
    fun `should throw exception when customer has already ordered the product`() {
        // given
        val orderRequest = OrderRequest(productId = "prd1234", email = "abc@def.com")
        val user = User(id=1, firstName = "George", lastName = "Bluth", email =  "abc@def.com")

        `when`(userService.retrieveUserResponse()).thenReturn(UserResponse(data = listOf(user)))
        `when`(orderRepository.existsByProductIdAndEmail("prd1234", "abc@def.com")).thenReturn(true)

        // when, then
        assertThrows(OrderAlreadyExistsException::class.java) {
            orderService.createOrder(orderRequest)
        }
        verify(orderRepository, times(1)).existsByProductIdAndEmail("prd1234", "abc@def.com")
        verify(orderRepository, never()).save(any(Order::class.java))
    }

    @Test
    fun `should get all orders`() {
        // given
        val orders = listOf(
            Order(id= 1L, email = "abc@def.com", productId = "prd1234",  firstName = "George", lastName = "Bluth"),
            Order(id= 2L, email = "abcd@def.com", productId = "prd12345",  firstName = "Divya", lastName = "Kumanan")
        )
        `when`(orderRepository.findAll()).thenReturn(orders)

        // when
        val result = orderService.getAllOrders()

        // then
        assertNotNull(result)
        assertEquals(2, result.size)
        assertEquals(orders, result)
    }

    @Test
    fun `should get all orders associated with email`() {
        // given
        val orders = listOf(
            Order(id= 1L, email = "abc@def.com", productId = "prd1234",  firstName = "George", lastName = "Bluth"),
            Order(id= 3L, email = "abc@def.com", productId = "prd12345",  firstName = "George", lastName = "Bluth")
        )
        `when`(orderRepository.findByEmail("abc@def.com")).thenReturn(orders)

        // when
        val result = orderService.getOrdersByEmail("abc@def.com")

        // then
        assertNotNull(result)
        assertEquals(2, result?.size)
        assertEquals(orders, result)
    }

    @Test
    fun `should get order based on order id`() {
        // given
        val order = Order(id= 1L, email = "abc@def.com", productId = "prd1234",  firstName = "George", lastName = "Bluth")
        `when`(orderRepository.findById(1L)).thenReturn(Optional.of(order))

        // when
        val result = orderService.getOrderByOrderId(1L)

        // then
        assertNotNull(result)
        assertEquals(order, result.get())
    }
}