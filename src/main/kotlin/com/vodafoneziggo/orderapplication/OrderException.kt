package com.vodafoneziggo.orderapplication

// Exception to indicate that the specified email does not exist in the users API
class UserNotFoundException(message: String) : RuntimeException(message)

// Exception to indicate that the specified order already exists
class OrderAlreadyExistsException(message: String) : RuntimeException(message)

// Exception to indicate that the specified order does not exist
class OrderNotFoundException(message: String) : RuntimeException(message)

// Exception to indicate that the call to User Api was unsuccessful
class UserApiException(message: String): RuntimeException(message)