package com.bjornno.ai_rag_functions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(scanBasePackages = ["com.bjornno.ai_rag_functions"])
class AiRagFunctionsApplication : SpringBootServletInitializer() {
	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(AiRagFunctionsApplication::class.java)
	}
}

fun main(args: Array<String>) {
	runApplication<AiRagFunctionsApplication>(*args)
}
