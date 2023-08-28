package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.config.security.JwtRequestDto
import ir.smartech.cro.storage.config.security.JwtResponseDto
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.service.AuthenticationService
import ir.smartech.cro.storage.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/account")
class AccountController(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {


    @PostMapping("/login")
    fun login(@RequestBody dto: JwtRequestDto): ResponseEntity<JwtResponseDto> {
        return ResponseEntity.ok(authenticationService.authenticate(dto.username, dto.password))
    }

    @PostMapping("/register")
    fun register(@RequestBody entity: User): ResponseEntity<JwtResponseDto> {
        val saved = userService.upsert(entity)
        return ResponseEntity.ok(authenticationService.authenticate(entity.username, entity.password))
    }
}