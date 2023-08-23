package ir.smartech.cro.analytics.rdb.entity

import jakarta.persistence.*
import java.util.*

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    @Column
    open var createdDate: Date? = null

    @Column
    open var modifiedDate: Date? = null

    @PrePersist
    fun setCreateDate() {
        createdDate = if (createdDate == null)
            Date()
        else
            createdDate
    }

    @PreUpdate
    fun setUpdateDate() {
        modifiedDate = Date()
    }
}