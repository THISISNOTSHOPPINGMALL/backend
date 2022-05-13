package com.hindsight.user_api.user

import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface UserRepository : CoroutineSortingRepository<User, Long> {

    suspend fun findByLoginId(loginId: String): User?

    suspend fun findByLoginIdAndPw(loginId: String, pw: String): User?
}
