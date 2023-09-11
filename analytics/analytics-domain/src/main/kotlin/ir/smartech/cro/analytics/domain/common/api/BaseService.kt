package ir.smartech.cro.analytics.domain.common.api

interface BaseService<TEntity> {

    fun upsert(entity: TEntity?): TEntity?

    fun findById(id: Int): TEntity?
    fun findByIdAndClientId(id: Int, clientId: Int): TEntity?

    fun findAll(): List<TEntity?>
    fun findAllClientId(clientId: Int): List<TEntity?>

    fun deleteById(id: Int, clientId: Int): Boolean

    fun delete(entity: TEntity?, clientId: Int): Boolean
}