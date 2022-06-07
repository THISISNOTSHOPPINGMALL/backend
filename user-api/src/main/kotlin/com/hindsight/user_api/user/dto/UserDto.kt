package com.hindsight.user_api.user.dto

import com.hindsight.core.validation.isAddress
import com.hindsight.core.validation.isPhoneNumber
import com.hindsight.user_api.user.User
import com.hindsight.user_api.validation.isUserId
import java.time.LocalDateTime
import java.time.ZoneId


data class UserDetailResponse(
    val loginId: String,
    val uniqueValue: String,
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
    val address: String,
    val phoneNumber: String,
    val token: String?
){
    companion object{
        fun from(user: User) = UserDetailResponse(
            loginId = user.loginId,
            uniqueValue = user.uniqueValue,
            createdAt = user.createdAt,
            address = user.address,
            phoneNumber = user.phoneNumber,
            token = user.token
        )
    }
}

data class LoginIdCheckRequest(
    val loginId: String
){
    fun isValid(): Boolean = loginId.isUserId()
}

data class UserAddRequest(
    val loginId: String,
    val pw: String,
    val address: String,
    val phoneNumber: String
){
    fun isValid(): Boolean = loginId.isUserId() && address.isAddress() && phoneNumber.isPhoneNumber()
}
