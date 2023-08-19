package ir.smartech.cro.storage.config.parquet

import org.apache.hadoop.conf.Configuration
import org.apache.parquet.column.ColumnDescriptor
import org.apache.parquet.hadoop.api.WriteSupport
import org.apache.parquet.io.ParquetEncodingException
import org.apache.parquet.io.api.Binary
import org.apache.parquet.io.api.RecordConsumer
import org.apache.parquet.schema.MessageType
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName
import java.lang.Boolean
import kotlin.Any
import kotlin.String


class CustomWriteSupport(schema: MessageType) : WriteSupport<List<String>>() {

    var schema: MessageType? = schema
    var recordConsumer: RecordConsumer? = null
    var cols: List<ColumnDescriptor>? = schema.columns


    override fun init(p0: Configuration?): WriteContext {
        return WriteContext(schema, HashMap())
    }

    override fun prepareForWrite(p0: RecordConsumer?) {
        this.recordConsumer = p0
    }

    override fun write(values: List<String>?) {
        if (values?.size !== cols?.size) {
            throw ParquetEncodingException(
                "Invalid input data. Expecting " +
                        cols!!.size + " columns. Input had " + values?.size + " columns (" + cols + ") : " + values
            )
        }

        recordConsumer!!.startMessage()
        for (i in cols!!.indices) {
            val `val`: String = values?.get(i)!!
            // val.length() == 0 indicates a NULL value.
            if (`val`.length > 0) {
                recordConsumer!!.startField(cols!![i].path[0], i)
                when (cols!![i].type) {
                    PrimitiveTypeName.BOOLEAN -> recordConsumer!!.addBoolean(Boolean.parseBoolean(`val`))
                    PrimitiveTypeName.FLOAT -> recordConsumer!!.addFloat(`val`.toFloat())
                    PrimitiveTypeName.DOUBLE -> recordConsumer!!.addDouble(`val`.toDouble())
                    PrimitiveTypeName.INT32 -> recordConsumer!!.addInteger(`val`.toInt())
                    PrimitiveTypeName.INT64 -> recordConsumer!!.addLong(`val`.toLong())
                    PrimitiveTypeName.BINARY -> recordConsumer!!.addBinary(stringToBinary(`val`))
                    else -> throw ParquetEncodingException(
                        "Unsupported column type: " + cols!![i].type
                    )
                }
                recordConsumer!!.endField(cols!![i].path[0], i)
            }
        }
        recordConsumer!!.endMessage()
    }

    private fun stringToBinary(value: Any): Binary {
        return Binary.fromString(value.toString())
    }
}
