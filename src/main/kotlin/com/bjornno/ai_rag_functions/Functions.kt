package com.bjornno.ai_rag_functions

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Description
import org.springframework.stereotype.Service

@JsonClassDescription("Request for the example function")
data class RequestJokeTheme @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val content: String = "test"
)

@JsonClassDescription("Request for the captain")
data class RequestBoatCaptain @JsonCreator
constructor (
    @JsonProperty @JsonPropertyDescription("Theme for the joke") val name: String = "Johan"
)


data class Response(val content: String)

@Service("boatDriverResolver")
@Description("When we have a theme for the joke, we also need to know the name of the main character")
class BoatDriverResolver: java.util.function.Function<RequestBoatCaptain, Response> {
    override fun apply(t: RequestBoatCaptain): Response {
        LoggerFactory.getLogger(BoatDriverResolver::class.java).info("Boat captain resolver called")
        return Response("The boat captains name is Kong Harald")
    }
}

@Service("jokeThemeResolver")
@Description("Joke theme resolver service. This service will provide a theme for the joke")
class JokeThemeResolver: java.util.function.Function<RequestJokeTheme, Response> {
    override fun apply(t: RequestJokeTheme): Response {
        LoggerFactory.getLogger(BoatDriverResolver::class.java).info("Joke theme resolver called")
        return Response("The joke should be about a boat. But I also need to know the name of the boat captain")
    }
}
