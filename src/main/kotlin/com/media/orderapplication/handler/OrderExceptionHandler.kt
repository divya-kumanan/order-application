package com.media.orderapplication.handler

import com.media.orderapplication.exception.OrderAlreadyExistsException
import com.media.orderapplication.exception.OrderNotFoundException
import com.media.orderapplication.exception.UserApiException
import com.media.orderapplication.exception.UserNotFoundException
import com.media.orderapplication.model.OrderErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@RestControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(OrderAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(ex: OrderAlreadyExistsException): OrderErrorResponse {
        return OrderErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message)
    }

    @ExceptionHandler(OrderNotFoundException::class, UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleOrderNotFoundException(ex: Exception): OrderErrorResponse {
        return OrderErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message)
    }

    @ExceptionHandler(UserApiException::class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    fun handleUserApiException(ex: UserApiException): OrderErrorResponse {
        return OrderErrorResponse(ex.hashCode(), ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: RuntimeException): Any {
        return if (ex is MethodArgumentTypeMismatchException) {
            val orderErrorResponse = OrderErrorResponse(HttpStatus.BAD_REQUEST.value(), "Order ID should be a number")
            ResponseEntity(orderErrorResponse, HttpStatus.BAD_REQUEST)
        } else {
            val orderErrorResponse = OrderErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message)
            ResponseEntity(orderErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}