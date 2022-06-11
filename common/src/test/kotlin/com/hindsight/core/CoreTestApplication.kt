package com.hindsight.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableCore
class CoreTestApplication

fun main(args: Array<String>) {
    runApplication<CoreTestApplication>(*args)
}
