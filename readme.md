
## How did you handle exceptions and why you choose this way ?

I have very little knowledge about bigger app where code can be used, then I decided to avoid fully exception handling.
If any unexpected situation occurs I just throw UserNotFoundException or InvalidUserRecord that should be handled
by bigger component

## Which mechanisms did you choose for data transformation? What was your motivation?

I only transform data fetched from "database" to CorrectUser format. I do this because I want to prepare clean user entity
and separate cleaning and display logic.g*

## Is function displayGrossSalary(userId:Int): Unit  signature optimal? If not than what would you change and why?

Yes. in this case signature is optimal because "display" function don't return any specified type.
In case where we have function "findGrossSalary"  or "getGrossSalary" then we should change signature because each
public function should have defined exact type 