package com.bjornno.ai_rag_functions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.bjornno.ai_rag_functions"])
class AiRagFunctionsApplication

fun main(args: Array<String>) {
	runApplication<AiRagFunctionsApplication>(*args)
}
