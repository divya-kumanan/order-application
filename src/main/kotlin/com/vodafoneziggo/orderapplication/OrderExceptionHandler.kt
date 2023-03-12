package com.vodafoneziggo.orderapplication

import com.vodafoneziggo.orderapplication.model.OrderErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConversion(ex: RuntimeException): OrderErrorResponse {
        return OrderErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message)
    }
}