// Define the Jenkins pipeline using the declarative syntax.
pipeline {
    // Specify the agent where the pipeline will run.
    // 'any' means the pipeline can run on any available agent.
    agent any

    // Define the tools to use
    tools {
      git 'Default'
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
        // Stage for checking out the source code from Git.
        stage('Checkout') {
            steps {
                // Checkout the source code from the specified Git repository and branch.
                git branch: 'main', url: 'https://github.com/managedkaos/java-calculator.git'
            }
        }

        // Stage for building the Java project using Maven.
        stage('Build') {
            steps {
                // The 'maven-3.9.9' tool must be configured in Jenkins Global Tool Configuration.
                // Execute Maven goals: clean, test, and package.
                sh 'mvn clean test package'
            }
        }

        // Stage for running the calculator application.
        stage('Run Calculator') {
            steps {
                // Execute a shell script to run the calculator JAR with parameters.
                // The parameters are accessed using the 'params.' prefix.
                // BUILD_ID is a built-in Jenkins environment variable.
                sh """
                    #!/bin/bash
                    set -f # turns off echoing
                    echo "      Build: $BUILD_ID"
                    echo "    Operation: ${params.OPERATION}"
                    echo " First number: ${params.NUMBER_1}"
                    echo "Second number: ${params.NUMBER_2}"
                    java -jar target/calculator-1.0-SNAPSHOT.jar ${params.OPERATION} ${params.NUMBER_1} ${params.NUMBER_2}
                """
            }
        }
    }
}