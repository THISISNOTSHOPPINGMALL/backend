package com.hindsight.core.validation


/**
 * format : 국가번호 전화번호
 * ex : 82 010-0000-0000
 */

val PHONE_NUMBER_REGEX = "[0-9]{1,4} [0-9]{3}-[0-9]{4}-[0-9]{4}".toRegex()
fun String.isPhoneNumber(): Boolean = PHONE_NUMBER_REGEX.matches(this)

const val ADDRESS_MAX_LENGTH = 255
fun String.isAddress(): Boolean = this.length <= ADDRESS_MAX_LENGTH && this.isNotBlank()