// Define the Jenkins pipeline using the declarative syntax.
pipeline {
    // Specify the agent where the pipeline will run.
    // 'any' means the pipeline can run on any available agent.
    agent any

    // Define the tools to use
    // The 'maven-X.Y.Z' tool must be configured
    // in Jenkins Global Tool Configuration
    // where 'X.Y.Z' is the latest version of Maven.
    tools {
      maven 'maven-3.9.9'
    }

    // Define parameters that can be passed to the pipeline.
    parameters {
        // Choice parameter for the calculator operation.
        choice(
            name: 'OPERATION',
            choices: ['add', 'subtract', 'multiply', 'divide'],
            description: 'Select the calculator operation to perform.'
        )
        // String parameter for the first number.
        string(
            name: 'NUMBER_1',
            defaultValue: '10',
            description: 'First number for calculation.'
        )
        // String parameter for the second number.
        string(
            name: 'NUMBER_2',
            defaultValue: '5',
            description: 'Second number for calculation.'
        )
    }

    // Define the stages of the pipeline.
    stages {
        // Stage for building the Java project using Maven.
        stage('Test') {
            steps {
                // Execute Maven goals: clean, test, and package.
                bat 'mvn clean test'
            }
        }

        // Stage for building the Java project using Maven.
        stage('Build') {
            steps {
                // Execute Maven goals: clean, test, and package.
                bat 'mvn package -DskipTests'
            }
        }

        // Stage for running the calculator application.
        stage('Deploy') {
            steps {
                // Execute a shell script to run the calculator JAR with parameters.
                // The parameters are accessed using the 'params.' prefix.
                // BUILD_ID is a built-in Jenkins environment variable.
                // Use 'bat' for Windows batch scripting
                bat """
                    @echo off
                    echo         Build: %BUILD_ID%
                    echo     Operation: %OPERATION%
                    echo  First number: %NUMBER_1%
                    echo Second number: %NUMBER_2%

                    REM Fallback to default values if parameters are not set
                    if "%OPERATION%"=="" set OPERATION=add
                    if "%NUMBER_1%"=="" set NUMBER_1=10
                    if "%NUMBER_2%"=="" set NUMBER_2=5

                    echo Using operation: %OPERATION%
                    echo Using first number: %NUMBER_1%
                    echo Using second number: %NUMBER_2%

                    java -jar target\\calculator-1.0-SNAPSHOT.jar %OPERATION% %NUMBER_1% %NUMBER_2%
                """
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/calculator-1.0-SNAPSHOT.jar',
                followSymlinks: false

            junit testResults: '**/target/surefire-reports/*.xml',
                stdioRetention: 'ALL'
        }
    }
}
