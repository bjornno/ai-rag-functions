Example spring boot project using spring-ai, function calling with kotlin.

First provide your spring.ai.openai.api-key in application.properties. 
Then start the app and go to http://localhost:8080/?wantedTheme=motorcycle (motorcycle, boat or car)

It register 4 functions (get theme for joke, get boat captain, get the car driver or the motorcycle driver)
Then ask the llm to tell a joke.
LLM resolves that we need to call the functions
And based on the results of the functions construct the joke.

Here's output from running the code with each wanted theme:

```
2024-10-29T15:06:54.263+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke based on car.
2024-10-29T15:07:03.147+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme car
2024-10-29T15:07:03.925+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-1] c.b.ai_rag_functions.CarDriverResolver   : Car driver resolver called
2024-10-29T15:07:04.885+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-1] o.springframework.stereotype.Controller  : Received response: Why did Michael Schumacher bring a ladder to the racetrack?

Because he heard the race was going to be a high-octane event, and he wanted to climb to victory!


2024-10-29T15:07:24.931+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-4] o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke based on motorcycle.
2024-10-29T15:07:26.018+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-4] c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme motorcycle
2024-10-29T15:07:26.753+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-4] c.b.a.MotorcycleDriverResolver           : Motorcycle driver resolver called
2024-10-29T15:07:27.557+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-4] o.springframework.stereotype.Controller  : Received response: Why did Valentino Rossi bring a ladder to the motorcycle race?

Because he heard the competition was really high!


2024-10-29T15:07:31.142+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-7] o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke based on boat.
2024-10-29T15:07:31.970+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-7] c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme boat
2024-10-29T15:07:35.576+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-7] c.b.ai_rag_functions.BoatDriverResolver  : Boat captain resolver called
2024-10-29T15:07:36.488+01:00  INFO 27835 --- [ai-rag-functions] [nio-8080-exec-7] o.springframework.stereotype.Controller  : Received response: Why did Captain Jack Sparrow bring a ladder to his boat?

Because he heard the drinks were on the house! 🏴‍☠️🚤

```
