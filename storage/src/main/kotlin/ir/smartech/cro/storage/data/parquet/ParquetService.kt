package ir.smartech.cro.storage.data.parquet

import ir.smartech.cro.storage.config.parquet.CustomParquetWriter
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import org.apache.hadoop.fs.Path
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.schema.MessageType
import org.apache.parquet.schema.MessageTypeParser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files


@Service
class ParquetService {

    @Value("\${schema.filePath}")
    private val schemaFilePath: String? = null

    @Value("\${output.directoryPath}")
    private val outputDirectoryPath: String? = null

    private val logger = LoggerFactory.getLogger(ParquetService::class.java)
    fun writeData(data: List<KafkaMessage>) {
        val columns: List<List<String>> = getDataForFile(data)
        val schema: MessageType = getSchemaForParquetFile()
        val writer: CustomParquetWriter = getParquetWriter(schema)

        for (column in columns) {
            logger.info("Writing line: $column")
            writer.write(column)
        }
        logger.info("Finished writing Parquet file.")

        writer.close()
    }

    private fun getParquetWriter(schema: MessageType): CustomParquetWriter {
        val outputFilePath: String = outputDirectoryPath + "/" + System.currentTimeMillis() + ".parquet"
        val outputParquetFile = File(outputFilePath)
        val path = Path(outputParquetFile.toURI().toString())
        return CustomParquetWriter(path, schema, false, CompressionCodecName.SNAPPY)
    }

    private fun getSchemaForParquetFile(): MessageType {
        val resource = ClassPathResource(
            schemaFilePath!!
        ).file
        val rawSchema = String(Files.readAllBytes(resource.toPath()))
        return MessageTypeParser.parseMessageType(rawSchema)
    }

    private fun getDataForFile(data: List<KafkaMessage>): List<List<String>> {
        return data.map { listOf(it.message!!, it.name!!, it.age.toString()) }
    }
}
