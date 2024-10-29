Example spring boot project using spring-ai, function calling with kotlin.

First provide your spring.ai.openai.api-key in application.properties. 
Then start the app and go to http://localhost:8080/

It register 2 functions (get theme for joke, and get main character for joke)
Then ask the llm to tell a joke.
LLM resolves that we need to call the functions
And based on the results of the functions construct the joke.

Here's output from running the code:

```
2024-10-29T14:10:27.326+01:00  INFO 21811 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke based on some theme.
2024-10-29T14:10:28.523+01:00  INFO 21811 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.BoatDriverResolver  : Joke theme resolver called
2024-10-29T14:10:29.187+01:00  INFO 21811 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.BoatDriverResolver  : Boat captain resolver called
2024-10-29T14:10:30.129+01:00  INFO 21811 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Received response: Here's a joke for you:

Why did Captain Kong Harald always carry a pencil in his life jacket?

Because he wanted to draw attention to himself whenever they were lost at sea!

```
