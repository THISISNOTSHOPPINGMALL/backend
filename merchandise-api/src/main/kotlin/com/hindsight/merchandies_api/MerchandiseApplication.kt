package com.hindsight.merchandies_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MerchandiseApplication

fun main(args: Array<String>) {
    runApplication<MerchandiseApplication>(*args)
}

