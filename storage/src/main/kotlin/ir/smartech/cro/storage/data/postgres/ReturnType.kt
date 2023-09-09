package ir.smartech.cro.storage.data.postgres

/**
 * the CRO acceptable column types that client sends in the ClientSchema creation api
 * and validates in 'receive data' api with the ClientSchema and these types
 */
enum class ReturnType {
    STRING, NUMBER, BOOLEAN, JSON
}