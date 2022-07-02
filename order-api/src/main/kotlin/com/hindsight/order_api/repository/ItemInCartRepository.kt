package com.hindsight.order_api.repository

import com.hindsight.order_api.domain.ItemInCartEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemInCartRepository: JpaRepository<ItemInCartEntity, Long>