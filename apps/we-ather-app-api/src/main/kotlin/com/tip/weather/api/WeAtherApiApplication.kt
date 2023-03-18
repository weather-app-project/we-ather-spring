package com.tip.weather.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeAtherApiApplication

fun main(args: Array<String>) {
    runApplication<WeAtherApiApplication>(*args)
}
