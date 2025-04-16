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

Right click on `test` folder -> `Mark Directory as` -> `Test Sources Root`

Right click on `ScoreboardTest` in the `test` folder → `Run 'ScoreboardTest'`

## Installation

  **1. Clone the repository**
  
     git clone https://github.com/ivanakovacevic01/live-football-scoreboard.git
     
Then open the project in IntelliJ IDEA.
  
  **2. Build the project**

Open the terminal inside IntelliJ or your command line, and run:

      mvn clean install


This will build the project and download all required dependencies.

  
  **3. Open and run the main class**
  
  **In IntelliJ navigate to** 
  
 `src/main/java/org/example/Main.java`
     
  **Right-click on the Main class and select:** 
  
  Run `Main.main()`
  
## How to download the library

The *Football Scoreboard Library* is available for download through GitHub Releases. You can obtain the JAR file by following these steps:

1. Navigate to the https://github.com/ivanakovacevic01/live-football-scoreboard/releases.
2. Find the latest release tagged with the version number you're interested in (`v1.0.0`).
3. Under the "Assets" section, click on the JAR file (`liveFootballScoreboard-1.0-SNAPSHOT.jar`) to download it.

Once downloaded, you can add this JAR to your project and start using it. 


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





