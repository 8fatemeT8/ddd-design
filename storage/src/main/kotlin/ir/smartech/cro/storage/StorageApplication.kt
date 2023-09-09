package ir.smartech.cro.storage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
open class StorageApplication

fun main(args: Array<String>) {
    runApplication<StorageApplication>(*args)
}