Example spring boot project using spring-ai, function calling with kotlin.

I register 2 functions (get theme for joke, and get main character for joke)
Then ask the llm to tell a joke.
LLM resolves that we need to call the functions
And based on the results of the functions construct the joke.

Here's output from running the code:

```
2024-10-29T13:55:59.135+01:00  INFO 19768 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke based on some theme.
2024-10-29T13:56:00.122+01:00  INFO 19768 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.BoatDriverResolver  : Joke theme resolver called
2024-10-29T13:56:00.696+01:00  INFO 19768 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.BoatDriverResolver  : Boat driver resolver called
2024-10-29T13:56:01.507+01:00  INFO 19768 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Received response: Here's a joke based on the theme of a boat:

Why did Kong Harald, the boat driver, bring a ladder to the dock?

Because he heard the boat's humor was on another level!

```
