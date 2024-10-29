Example spring boot project using spring-ai, function calling with kotlin.

First provide your spring.ai.openai.api-key in application.yml. 
Then start the app and go to http://localhost:8080/?theme=2 (theme could be 1, 2 or 3)

It register 4 functions (get theme for joke, get boat captain, get the car driver or the motorcycle driver)
Then ask the llm to tell a joke.
LLM resolves that we need to call the functions
And based on the results of the functions construct the joke.

Here's output from running the code with each wanted theme:

```
o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke with theme number 2.
c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme 2
c.b.ai_rag_functions.CarDriverResolver   : Car driver resolver called
o.springframework.stereotype.Controller  : Received response: 

Why did Michael Schumacher bring a ladder to the racetrack?
Because he heard the race was going to be a high-octane event, and he wanted to climb to victory!

o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke with theme number 3.
c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme 3
c.b.a.MotorcycleDriverResolver           : Motorcycle driver resolver called
o.springframework.stereotype.Controller  : Received response: 

Why did Valentino Rossi bring a ladder to the motorcycle race?
Because he heard the competition was really high!

o.springframework.stereotype.Controller  : Sending prompt: Tell me a joke with theme number 1.
c.b.ai_rag_functions.JokeThemeResolver   : Joke theme resolver called with wanted theme 1
c.b.ai_rag_functions.BoatDriverResolver  : Boat captain resolver called
o.springframework.stereotype.Controller  : Received response: 

Why did Captain Jack Sparrow bring a ladder to his boat?
Because he heard the drinks were on the house! 🏴‍☠️🚤

```
