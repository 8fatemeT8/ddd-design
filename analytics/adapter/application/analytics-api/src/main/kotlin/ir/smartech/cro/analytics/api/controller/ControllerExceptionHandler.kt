package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * this controller catch ResponseException and return meaningful response
 */
@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(ResponseException::class)
    fun handleResponseException(ex: ResponseException): ResponseEntity<*>? {
        return ResponseEntity.status(ex.status!!).body(ex.message)
    }
}