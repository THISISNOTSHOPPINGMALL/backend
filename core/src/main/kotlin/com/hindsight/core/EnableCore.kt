package com.hindsight.core

import com.hindsight.core.exception.ExceptionHandler
import org.springframework.context.annotation.Import

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(ExceptionHandler::class)
annotation class EnableCore
