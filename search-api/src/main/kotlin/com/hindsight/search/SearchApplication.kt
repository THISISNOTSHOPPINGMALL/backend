package com.hindsight.search

import com.hindsight.core.EnableCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableCore
class SearchApplication

fun main(args: Array<String>) {
    runApplication<SearchApplication>(*args)
}