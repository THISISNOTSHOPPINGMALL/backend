package com.hindsight.user

import com.hindsight.core.EnableCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableCore
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}

