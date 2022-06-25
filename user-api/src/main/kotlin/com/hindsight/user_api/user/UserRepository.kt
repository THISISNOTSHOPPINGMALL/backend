package com.hindsight.user_api.user

import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface UserRepository : CoroutineSortingRepository<User, Long> {

    suspend fun findByLoginId(loginId: String): User?

    suspend fun findByToken(token: String): User?

    suspend fun findByUniqueValue(uniqueValue: String): User?
}
