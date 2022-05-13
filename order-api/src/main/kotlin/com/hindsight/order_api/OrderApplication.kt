package com.hindsight.order_api

import com.hindsight.core.EnableCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableCore
class OrderApplication

fun main(args: Array<String>) {
    runApplication<OrderApplication>(*args)
}