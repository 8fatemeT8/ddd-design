package ir.smartech.cro.analytics.rdb.mapper

import ir.smartech.cro.analytics.rdb.entity.BaseEntity

/**
 * this class provide the map between domain classes and entity classes
 * the TSource is the domain class
 * the TDestination is the entity class
 */
interface Mapper<TSource : ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity, TDestination : BaseEntity> {
    fun toSource(dto: TDestination): TSource
    fun toDestination(dto: TSource): TDestination
}