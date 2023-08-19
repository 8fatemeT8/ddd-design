package ir.smartech.cro.storage.config.parquet

import org.apache.hadoop.fs.Path
import org.apache.parquet.hadoop.ParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.schema.MessageType

class CustomParquetWriter(
    file: Path,
    schema: MessageType,
    enableDictionary: Boolean,
    codecName: CompressionCodecName
) :
    ParquetWriter<List<String>>(
        file,
        CustomWriteSupport(schema),
        codecName,
        DEFAULT_BLOCK_SIZE,
        DEFAULT_PAGE_SIZE,
        enableDictionary,
        false
    )
