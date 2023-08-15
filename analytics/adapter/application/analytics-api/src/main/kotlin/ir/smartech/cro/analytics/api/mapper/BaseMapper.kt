package ir.smartech.cro.analytics.api.mapper

import ir.smartech.cro.analytics.api.dto.BaseCreateDto
import ir.smartech.cro.analytics.api.dto.BaseEditDto
import ir.smartech.cro.analytics.api.dto.BaseListDto
import ir.smartech.cro.analytics.api.dto.BaseViewDto

interface BaseMapper<TEntity, TCreate : BaseCreateDto, TEdit : BaseEditDto, TView : BaseViewDto, TList : BaseListDto> {

    fun toEntity(create: TCreate?): TEntity?
    fun createToListEntity(create: List<TCreate?>?): List<TEntity?>?

    fun toEntity(edit: TEdit?): TEntity?
    fun editToListEntity(edit: List<TEdit?>?): List<TEntity?>?

    fun toView(entity: TEntity?): TView?
    fun toView(entity: List<TEntity?>?): List<TView?>?
//    fun toView(entity: Page<TEntity>): Page<TView>


    fun toList(entity: TEntity?): TList?
//    fun toList(entity: Page<TEntity>): Page<TList>
    fun toList(entity: List<TEntity?>?): List<TList?>?
}