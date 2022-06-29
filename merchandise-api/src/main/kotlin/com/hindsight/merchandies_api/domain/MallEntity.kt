package com.hindsight.merchandies_api.domain

import com.hindsight.merchandies_api.domain.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "mall")
class MallEntity private constructor(
    name: String,
    description: String,
    userId: Long
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mall_id")
    val id: Long = 0

    @Column(nullable = false)
    var name: String = name

    @Column(nullable = false)
    var description: String = description

    @Column(nullable = false)
    val userId: Long = userId

}
