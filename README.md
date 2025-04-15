# Live football scoreboard
A simple Java library for managing live football World Cup matches. The library allows starting new matches, updating scores, finishing matches and retrieving a summary of all ongoing matches. The library is reusable and easy to integrate into other applications.

## Features 
- **Start a match:** Initialize a new match between two teams.
- **Update scores:** Modify the scores during an ongoing match.
- **Finish match:** Removing a match from the scoreboard.
- **Retrieve match summaries:** Access live match data, including team names and scores.

## Technologies 
- **Java 22** – Core programming language used to implement business logic and object-oriented design.
- **Maven** – For project management and build automation.
- **JUnit 5** – Used for unit testing.
- **IntelliJ IDEA** – Recommended IDE for development and testing.

## Testing 
The project is developed following the Test-Driven Development (TDD) approach. Unit tests are written using JUnit 5 and they are placed in the test package.

To run all tests:

Right click on `ScoreboardTest` in the `test` folder → Run 'ScoreboardTest'

## Installation

  **1. Clone the repository**
  
     git clone https://github.com/ivanakovacevic01/live-football-scoreboard.git
  
  **2. Build the project**
  
  **3. Use the generated JAR in other projects**

     target/football-scoreboard-library-1.0-SNAPSHOT.jar

  If you want to include this library in another Maven project, install it locally:
  
      mvn install:install-file \ 
      -Dfile=target/football-scoreboard-library-1.0-SNAPSHOT.jar \
      -DgroupId=org.example \
      -DartifactId=football-scoreboard-library \
      -Dversion=1.0-SNAPSHOT \
      -Dpackaging=jar
  
  Then add it to your pom.xml:
  
  ```xml
  <dependency>
      <groupId>org.example</groupId>
      <artifactId>football-scoreboard-library</artifactId>
      <version>1.0-SNAPSHOT</version>
  </dependency>
  ```

## How to use in IntelliJ
**1. Open IntelliJ IDEA → `Open` project folder**
 
**2. Use `Main` class for testing**

**3. Run with right-click → `Run Main.main()`**



