package com.hindsight.core

import com.hindsight.core.exception.GlobalException
import com.hindsight.core.exception.GlobalMessage
import org.springframework.stereotype.Service

@Service
class CoreTestService {
    suspend fun throwTest(){
        throw GlobalException(GlobalMessage.TEST_EXCEPTION)
    }
}