# Study Point Exercise 6 in PBA Testing

# Only test code that makes sense, so skip Joke, and Jokes - why?
* because Joke and Jokes basically don't have any functionality to test

# Testing the DateFormatter class 
* It has problems and is untestable because it has non-deterministic behaviour: the new Date(), which always give the current Date.
 It needs to be refactored, so that it is given as an argument to the method getFormattedDate.