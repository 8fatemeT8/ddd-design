package ir.smartech.cro.storage.data.clickhouse

import cc.blynk.clickhouse.ClickHouseConnection
import cc.blynk.clickhouse.ClickHouseDataSource
import ir.smartech.cro.storage.data.clickhouse.dto.ClickhouseMessage
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement


@Repository
class ClickhouseService(
    private val dataSource: ClickHouseDataSource
) {

    // TODO maybe need to remove this method
    fun createTable(query: String? = null) {
        val connection: ClickHouseConnection = dataSource.connection
//        connection.createStatement().execute("DROP TABLE IF EXISTS CRO.test2")
        connection.createStatement().execute(
            query ?: "CREATE TABLE CRO.test3 (message String , name String,age Int32) ENGINE = Log()"
        )
    }

    fun insertValue(message: ClickhouseMessage) {
        val statement = prepareStatement()
        fillStatement(statement, message)
        statement.execute()
        statement.close()
    }

    fun insertValues(message: List<ClickhouseMessage>) {
        val statement = prepareStatement()
        message.forEach {
            fillStatement(statement, it)
            statement.addBatch()
        }
        statement.executeBatch()
        statement.close()
    }


    fun readData(): List<ClickhouseMessage> {
        val statement = dataSource.connection.createStatement()
        val resultSet = statement.executeQuery("SELECT * from CRO.test2")
        val result = arrayListOf<ClickhouseMessage>()
        while (resultSet.next()) {
            // TODO change logic based on clickhouseMessage fields
            result.add(ClickhouseMessage().apply {
                message = resultSet.getString(1)
                name = resultSet.getString(2)
                age = resultSet.getInt(3)
            })
        }
        resultSet.close()
        statement.close()
        return result
    }


    private fun prepareStatement(): PreparedStatement = dataSource.connection.prepareStatement(
        "INSERT INTO CRO.test2 (message, name ,age) VALUES (?, ?, ?)"
    )

    private fun fillStatement(statement: PreparedStatement, message: ClickhouseMessage) {
        // TODO change logic based on clickhouseMessage fields
        statement.setString(1, message.message)
        statement.setString(2, message.name)
        statement.setInt(3, message.age!!)
    }
}