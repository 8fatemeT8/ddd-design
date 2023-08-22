package ir.smartech.cro.analytics.rdb.mapper

interface Mapper<TSource, TDestination> {
    fun toSource(dto: TDestination?): TSource?
    fun toDestination(dto: TSource?): TDestination?
}