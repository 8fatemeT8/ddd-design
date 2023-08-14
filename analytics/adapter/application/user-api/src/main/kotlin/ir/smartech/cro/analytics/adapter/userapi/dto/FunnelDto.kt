package ir.smartech.cro.analytics.adapter.userapi.dto

import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType
import java.util.*

data class FunnelCreateDto(
    var name: String? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseCreateDto()

data class FunnelEditDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseEditDto()

data class FunnelViewDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseViewDto()

data class FunnelListDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseListDto()

//interface IList : BaseModel.IList {
//    override fun getProperties(): Set<IProperty> = listProps(
//        model(Funnel::project, field(Project::id), field(Project::name)),
//        model(Funnel::createdBy, field(User::presentation)),
//        model(Funnel::modifiedBy, field(User::presentation)),
//        model(
//            Funnel::steps,
//            field(Step::eventName),
//            field(Step::id),
//            field(Step::seq),
//            model(
//                Step::conditions,
//                model(
//                    StepCondition::property,
//                    field(Property::id),
//                    field(Property::propertyType),
//                    field(Property::name)
//                ),
//                field(StepCondition::operator),
//                field(StepCondition::value),
//            )
//        )
//    )
//}
//
//interface IView : BaseModel.IView {
//    @JvmDefault
//    override fun getProperties(): Set<IProperty> = viewProps(
//        field(Funnel::id),
//        field(Funnel::startDate),
//        field(Funnel::expDate),
//        field(Funnel::enable),
//        field(Funnel::eventType),
//        field(Funnel::slug),
//        model(Funnel::commandExecutive, field(LastCommandView::executionState), field(LastCommandView::createdAt)),
//        field(Funnel::name),
//        model(Funnel::createdBy, field(User::presentation)),
//        model(Funnel::modifiedBy, field(User::presentation)),
//        field(Funnel::createdDate),
//        field(Funnel::modifiedDate),
//        model(
//            Funnel::steps,
//            field(Step::eventName),
//            field(Step::seq),
//            model(
//                Step::conditions,
//                model(
//                    StepCondition::property,
//                    field(Property::id),
//                    field(Property::propertyType),
//                    field(Property::name)
//                ),
//                field(StepCondition::operator),
//                field(StepCondition::value),
//            )
//        )
//    )
//}
//
//interface ICreate : BaseModel.ICreate {
//    @JvmDefault
//    override fun getProperties(): Set<IProperty> =
//        createProps(
//            field(Funnel::enable),
//            field(Funnel::startDate),
//            field(Funnel::expDate),
//            field(Funnel::name),
//            field(Funnel::eventType),
//            field(Funnel::slug),
//            model(
//                Funnel::steps,
//                field(Step::eventName),
//                field(Step::seq),
//                model(
//                    Step::conditions,
//                    model(
//                        StepCondition::property,
//                        field(Property::id)
//                    ),
//                    field(StepCondition::operator),
//                    field(StepCondition::value),
//                )
//            )
//        )
//}
//
//interface IEdit : BaseModel.IEdit {
//    @JvmDefault
//    override fun getProperties(): Set<IProperty> = editProps(
//        field(Funnel::startDate),
//        field(Funnel::expDate),
//        field(Funnel::enable),
//        field(Funnel::eventType),
//        field(Funnel::slug),
//        field(Funnel::name),
//        model(
//            Funnel::steps,
//            field(Step::eventName),
//            field(Step::seq),
//            model(
//                Step::conditions,
//                model(
//                    StepCondition::property,
//                    field(Property::id),
//                ),
//                field(StepCondition::operator),
//                field(StepCondition::value),
//            )
//        )
//    )
//}
