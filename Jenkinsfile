pipeline {
  agent any
  stages {
    stage('Check for pom') {
      steps {
        fileExists 'pom.xml'
      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn compile test package'
      }
    }

    stage('Post build steps') {
      steps {
        writeFile(file: 'status.txt', text: 'It is fonderful!!')
      }
    }

  }
}