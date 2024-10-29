package com.bjornno.ai_rag_functions

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Description
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@JsonClassDescription("Request for the example function")
data class Request @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val content: String = "test"
)

@JsonClassDescription("Request for the driver")
data class RequestDriver @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val name: String = "Johan"
)


data class Response(val content: String)

@Service("boatDriverResolver")
@Description("When we have a theme for the joke, we also need to know the name of the main character")
class BoatDriverResolver: java.util.function.Function<RequestDriver, Response> {
    override fun apply(t: RequestDriver): Response {
        return Response("The boat drivers name is Kong Harald")
    }
}

@Service("jokeThemeResolver")
@Description("Joke theme resolver service. This service will provide a theme for the joke")
class JokeThemeResolver: java.util.function.Function<Request, Response> {
    override fun apply(t: Request): Response {
        return Response("The joke should be about a boat. But I also need to know the name of the boat driver")
    }
}


@Controller
class Controller(val builder: ChatClient.Builder) {
    @GetMapping("/")
    @ResponseBody
    fun getHello(): String {
        val chatClient: ChatClient = builder.build()
        val response: String = chatClient.prompt("Tell me a joke based on some theme.")
            .functions("jokeThemeResolver", "boatDriverResolver")
            .call().content()
        return response
    }
}
