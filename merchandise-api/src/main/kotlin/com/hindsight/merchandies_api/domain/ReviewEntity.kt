package com.hindsight.merchandies_api.domain

import com.hindsight.merchandies_api.domain.base.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "review")
class ReviewEntity private constructor(
    rate: Int,
    content: String,
    userId: Long,
    item: ItemEntity
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    val id: Long = 0

    @Column(nullable = false)
    var rate: Int = rate

    @Column(nullable = false)
    var content: String = content

    @Column(nullable = false)
    val userId: Long = userId

    var deletedAt: LocalDateTime? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    val item: ItemEntity = item

}
