package com.bjornno.ai_rag_functions

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DesentralizeTest {

    @Test
    fun shouldWork() {
        val objectMapper = ObjectMapper()
        objectMapper.registerModules(KotlinModule())
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val content = "{\"content\":\"joke theme\"}"
        val value = objectMapper.readValue(content, Request::class.java)
        assertEquals("joke theme", value.content)

    }
}
