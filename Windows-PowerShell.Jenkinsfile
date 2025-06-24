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
                // Use PowerShell for more reliable parameter handling on Windows
                powershell """
                    Write-Host "        Build: $env:BUILD_ID"
                    Write-Host "    Operation: $env:OPERATION"
                    Write-Host " First number: $env:NUMBER_1"
                    Write-Host "Second number: $env:NUMBER_2"

                    # Fallback to default values if parameters are not set
                    if (-not $env:OPERATION) { $env:OPERATION = 'add' }
                    if (-not $env:NUMBER_1) { $env:NUMBER_1 = '10' }
                    if (-not $env:NUMBER_2) { $env:NUMBER_2 = '5' }

                    Write-Host "Using operation: $env:OPERATION"
                    Write-Host "Using first number: $env:NUMBER_1"
                    Write-Host "Using second number: $env:NUMBER_2"

                    java -jar target\\calculator-1.0-SNAPSHOT.jar $env:OPERATION $env:NUMBER_1 $env:NUMBER_2
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
