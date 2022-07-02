package com.hindsight.order_api.repository

import com.hindsight.order_api.domain.CartEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<CartEntity, Long>
