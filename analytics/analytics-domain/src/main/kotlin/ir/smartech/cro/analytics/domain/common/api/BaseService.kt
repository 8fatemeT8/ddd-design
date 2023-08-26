package ir.smartech.cro.analytics.domain.common.api

interface BaseService<TEntity> {

    fun upsert(entity: TEntity?): TEntity?

    fun findById(id: Int): TEntity?
    fun findByIdAndProjectId(id: Int, projectId: Int): TEntity?

    fun findAll(): List<TEntity?>
    fun findAllProjectId(projectId: Int): List<TEntity?>

    fun deleteById(id: Int, projectId: Int): Boolean

    fun delete(entity: TEntity?, projectId: Int): Boolean
}