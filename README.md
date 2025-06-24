# Java Calculator

This project uses Java to implement a command line calculator.

Use the following tools to **test**, **build** and **package** the application:

- [Jenkins](https://www.jenkins.io/)
- [Git](https://git-scm.com/)
- [Maven](https://maven.apache.org/)

## Notes

Review the following file for notes on the project: [`NOTES.md`](./NOTES.md).

## Prerequisites

You should have the following in place before you start:

- Java Development Kit (JDK) version 21
- Jenkins installed and running
- Tool configured in Jenkins:
  - Git
  - Maven

## Freestyle Job Configuration

1. **Create a new freestyle job**:
   - Go to Jenkins Dashboard
   - Select "New Item"
   - Enter a name (e.g., "Calculator-Build")
   - Select "Freestyle project"
   - Select "OK"

1. **Source Code Management**:
   - Select Git
   - Enter the repository URL for this project
   - Specify the `*/main` branch

1. **Build Steps**:
   - Add a build step using **Invoke top-level Maven targets**.
   - Under **Maven Version**, select the version of Maven configured for the system.
   - Add the following under **Goals**: `clean test package`

1. **Additional Build Step** (to run the JAR):
   - macOS, Linux and Docker: Add "Execute shell" build step
   - Windows: Add ""Execute Windows batch command" build step
   - Command: `java -jar target/calculator-1.0-SNAPSHOT.jar add 10 5`

1. **Post-build Actions**:
   - Add "Publish JUnit test result report"
   - Test report XMLs: `**/target/surefire-reports/*.xml`
   - Add "Archive the artifacts"
   - Files to archive: `**/target/calculator-1.0-SNAPSHOT.jar`

1. Save and run the job.

1. Review the console output.  A successful run should include text simliar to the following:

  ```text
  + java -jar target/calculator-1.0-SNAPSHOT.jar add 10 5
  Result of 10.00 + 5.00 = 15.00
  Archiving artifacts
  Recording test results
  ```

## Freestyle Job Configuration with Parameters

Add the following parameters to your freestyle job:

### Choice Parameters:

Required:

- Name: `OPERATION`
  - Choices: `add`, `subtract`, `multiply`, `divide`
  - Description: "Select the calculator operation to perform"

### String Parameters:

Required:

- Name: `NUMBER_1`
  - Default value: `10`
  - Description: "First number for calculation"

- Name: `NUMBER_2`
  - Default value: `5`
  - Description: "Second number for calculation"

### Running the JAR with Parameters

Replace the JAR execution step with this parametrized version:

```bash
echo "Executing calculator with operation: $OPERATION, numbers: $NUMBER_1 and $NUMBER_2"
java -jar target/calculator-1.0-SNAPSHOT.jar $OPERATION $NUMBER_1 $NUMBER_2
```
