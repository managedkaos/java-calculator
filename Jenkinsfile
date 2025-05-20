pipeline {
  agent any
  stages {
    stage('Checkout Scm') {
      steps {
        git 'https://github.com/managedkaos/java-calculator.git'
      }
    }

    stage('Maven Build 0') {
      steps {
        sh 'mvn clean test package'
      }
    }

    stage('Shell script 1') {
      steps {
        sh '''#!/bin/bash
echo "Executing calculator with operation: $OPERATION, numbers: $NUMBER_1 and $NUMBER_2"
java -jar target/calculator-1.0-SNAPSHOT.jar $OPERATION $NUMBER_1 $NUMBER_2'''
      }
    }

  }
  tools {
    maven 'maven-3.9.9'
  }
  post {
    always {
      step(artifacts: '**/target/calculator-1.0-SNAPSHOT.jar', followSymlinks: false, $class: 'ArtifactArchiver')
      step(allowEmptyResults: true, keepProperties: true, stdioRetention: 'ALL', checksName: '', keepTestNames: true, $class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml')
    }

  }
  parameters {
    choice(name: 'OPERATION', choices: [add, subtract, multiply, divide], description: 'Select the calculator operation to perform')
    string(name: 'NUMBER_1', defaultValue: '10', description: 'First number for calculation')
    string(name: 'NUMBER_2', defaultValue: '5', description: 'Second number for calculation')
  }
}