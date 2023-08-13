package ir.smartech.cro.analytics.adapter.userapi.dto

data class FunnelCreateDto(var name: String) : BaseCreateDto()
data class FunnelEditDto(var name: String) : BaseEditDto()
data class FunnelViewDto(var name: String) : BaseViewDto()
data class FunnelListDto(var name: String) : BaseListDto()
