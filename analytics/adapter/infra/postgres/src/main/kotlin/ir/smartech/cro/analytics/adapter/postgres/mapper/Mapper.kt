package ir.smartech.cro.analytics.adapter.postgres.mapper

open interface Mapper<TSource, TDestination> {
    fun toSource(dto: TSource?): TDestination?
    fun toDestination(dto: TDestination?): TSource?
}