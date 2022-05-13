package com.hindsight.user_api.validation

const val LOGIN_ID_MAX_LENGTH = 16
fun String.isUserId() = this.length <= LOGIN_ID_MAX_LENGTH && this.isNotBlank()