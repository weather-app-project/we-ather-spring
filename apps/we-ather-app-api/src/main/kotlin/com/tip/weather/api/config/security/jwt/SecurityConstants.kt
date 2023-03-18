package com.tip.weather.api.config.security.jwt

const val TOKEN_HEADER = "Authorization"
const val TOKEN_PREFIX = "Bearer "
const val TOKEN_TYPE = "JWT"
const val TOKEN_ISSUER = "we-ahter-api"
const val TOKEN_AUDIENCE = "we-ahter-app"
const val ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 30 // 30분
