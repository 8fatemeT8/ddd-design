package ir.smartech.cro.analytics.adapter.postgres.entity

import jakarta.persistence.*
import java.util.*

@MappedSuperclass
abstract class BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    open var id: Int? = null

    @Version
    @Column(nullable = false)
    open var crc: Int? = null

    @Column
    open var createdDate: Date? = null

    @Column
    open var modifiedDate: Date? = null

//    @ManyToOne(fetch = FetchType.LAZY)
//    open var createdBy: User? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    open var modifiedBy: User? = null

}