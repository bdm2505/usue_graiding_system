package ru.lytvest.kafedra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafedraApplication

fun main(args: Array<String>) {
	runApplication<KafedraApplication>(*args)
}
