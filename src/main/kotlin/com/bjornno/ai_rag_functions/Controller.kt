package com.bjornno.ai_rag_functions

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Description
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody



@Controller
class Controller(val builder: ChatClient.Builder) {
    @GetMapping("/")
    @ResponseBody
    fun getHello(): String {
        val chatClient: ChatClient = builder.build()
        val prompt = "Tell me a joke based on some theme."
        val logger = LoggerFactory.getLogger(Controller::class.java)
        logger.info("Sending prompt: $prompt")
        val response: String = chatClient.prompt(prompt)
            .functions("jokeThemeResolver", "boatDriverResolver")
            .call().content()
        logger.info("Received response: $response")
        return response
    }
}
