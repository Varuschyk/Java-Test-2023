# Test Java 2023

## If project occurred compile error
```
First in all after project clone, in e2e module may
java directory be not marked as `Test root directory`, 
and resources too.
Second that may be, it output module path problem,
to fix that, go to `Project Structure` in `Compiler output`
specify the following: "~\IdeaProjects\testOverviewApplication\out"
```

## Security - Manager/Client
```
Unfortunately, I was partially unable to 
complete the assignment requirement. 
Because for unknown reasons to me, 
the security configuration behaves strangely.
```

## E2E Tests
```
I created only one test scenario as example. 
I didn't describe each scenario, since this would be 
too cumbersome and would take a lot of time.
```

## Build

---
```bash
cd path/to/project/

./mvnw clean install
./mvnw compile
```