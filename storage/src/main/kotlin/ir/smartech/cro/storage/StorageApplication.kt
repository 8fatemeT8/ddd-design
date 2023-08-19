package ir.smartech.cro.storage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class StorageApplication

fun main(args: Array<String>) {
    runApplication<StorageApplication>(*args)
}