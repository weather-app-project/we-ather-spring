package com.tip.weather.api.config.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class JwtTokenProvider {

    @Value("\${jwt.sign}")
    lateinit var signingKey: String

    fun generateHttpRequestToken(deviceNum: String): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(signingKey.toByteArray()), SignatureAlgorithm.HS512)
            .setHeaderParam("typ", TOKEN_TYPE)
            .setIssuer(TOKEN_ISSUER)
            .setAudience(TOKEN_AUDIENCE)
//            .setClaims(deviceNum)
            .setSubject("" + deviceNum)
            .setExpiration(Date(Date().time + ACCESS_TOKEN_EXPIRE_TIME))
//            .claim("rol", roles)
            .compact()
    }
}