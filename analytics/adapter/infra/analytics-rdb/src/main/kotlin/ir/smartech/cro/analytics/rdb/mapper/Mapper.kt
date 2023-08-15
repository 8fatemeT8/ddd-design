package ir.smartech.cro.analytics.rdb.mapper

interface Mapper<TSource, TDestination> {
    fun toSource(dto: TSource?): TDestination?
    fun toDestination(dto: TDestination?): TSource?
}