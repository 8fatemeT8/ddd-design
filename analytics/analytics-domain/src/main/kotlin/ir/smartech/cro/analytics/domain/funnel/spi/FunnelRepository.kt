package ir.smartech.cro.analytics.domain.funnel.spi

import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import java.util.Optional

interface FunnelRepository {
    fun save(entity: Funnel): Funnel?
    fun findById(id: Int): Optional<Funnel>
    fun findAll(): Iterable<Funnel?>
    fun deleteById(id: Int)
    fun delete(entity: Funnel)
}