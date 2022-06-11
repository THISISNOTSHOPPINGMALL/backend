package com.hindsight.core

import com.hindsight.core.exception.GlobalException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CoreTestApplication::class])
internal class CoreTest(private val coreTestService: CoreTestService) : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        "Core가 잘 로드 되는지" {

        }

        "Exception이 잘 발생되는지" {
            shouldThrow<GlobalException> {
                coreTestService.throwTest()
            }
        }
    }
}