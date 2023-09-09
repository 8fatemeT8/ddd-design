package ir.smartech.cro.storage.config.security

data class JwtRequestDto(var username: String? = null, var password: String? = null)
data class JwtResponseDto(var jwtToken: String? = null)