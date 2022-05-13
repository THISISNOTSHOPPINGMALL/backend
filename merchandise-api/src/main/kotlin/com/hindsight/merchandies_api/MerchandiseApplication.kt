package com.hindsight.merchandies_api

import com.hindsight.core.EnableCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableCore
class MerchandiseApplication

fun main(args: Array<String>) {
    runApplication<MerchandiseApplication>(*args)
}