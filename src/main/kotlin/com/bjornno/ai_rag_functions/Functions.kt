package com.bjornno.ai_rag_functions

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Description
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

interface LLMFunction<A,B>: java.util.function.Function<A, B> {
    override fun apply(a: A): B {
        return invoke(a)
    }

    fun invoke(a: A): B
}

@JsonClassDescription("Request for the example function")
data class RequestJokeTheme @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val requestedTheme: Int = 0
)

@JsonClassDescription("Request for the driver")
data class RequestDriver @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val themeName: String = "cars"
)

data class Response(val content: String)

@Service("boatDriverResolver")
@Description("When we have a theme for the joke, we also need to know the name of the main character")
class BoatDriverResolver: LLMFunction<RequestDriver, Response> {
    override fun invoke(t: RequestDriver): Response {
        LoggerFactory.getLogger(BoatDriverResolver::class.java).info("Boat captain resolver called")
        return Response("The boat captains name is Jack Sparrow")
    }
}


@Service("carDriverResolver")
@Description("When we have a theme for the joke, we also need to know the name of the main character")
class CarDriverResolver: LLMFunction<RequestDriver, Response> {
    override fun invoke(t: RequestDriver): Response {
        LoggerFactory.getLogger(CarDriverResolver::class.java).info("Car driver resolver called")
        return Response("The car driver is called Michael Schumacher")
    }
}

@Service("motorcycleDriverResolver")
@Description("When we have a theme for the joke, we also need to know the name of the main character")
class MotorcycleDriverResolver: LLMFunction<RequestDriver, Response> {
    override fun invoke(t: RequestDriver): Response {
        LoggerFactory.getLogger(MotorcycleDriverResolver::class.java).info("Motorcycle driver resolver called")
        return Response("The motorcycle driver is called Valentino Rossi")
    }
}

@Service("jokeThemeResolver")
@Description("Joke theme resolver service. This service will provide a theme for the joke")
class JokeThemeResolver: LLMFunction<RequestJokeTheme, Response> {
    override fun invoke(t: RequestJokeTheme): Response {
        LoggerFactory.getLogger(JokeThemeResolver::class.java).info("Joke theme resolver called with wanted theme ${t.requestedTheme}")
        return when(t.requestedTheme) {
            1 -> Response("The joke should be about a boat, but I also need to know the name of the boat captain")
            2 -> Response("The joke should be about a car, but I also need to know the name of the car driver")
            3 -> Response("The joke should be about a motorcycle, but I also need to know the name of the motorcycle driver")
            else -> Response("The joke should be about a boat, but I also need to know the name of the boat captain")
        }
    }
}

fun interface Jokey: (Int) -> String

@Controller
class Controller(val builder: ChatClient.Builder,
    val applicationContext: ApplicationContext): Jokey {
    @GetMapping("/")
    @ResponseBody
    override fun invoke(@RequestParam theme: Int): String {
        val chatClient: ChatClient = builder.build()
        val prompt = "Tell me a joke with theme number $theme."
        val logger = LoggerFactory.getLogger(Controller::class.java)
        logger.info("Sending prompt: $prompt")
        val response: String = chatClient.prompt(prompt)
            .functions(*applicationContext.getBeanNamesForType(LLMFunction::class.java))
            .call().content()
        logger.info("Received response: $response")
        return response
    }
}
