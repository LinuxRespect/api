pipeline {
  agent any
  stages {
    stage('Check for pom') {
      steps {
        fileExists 'pom.xml'
      }
    }

    stage('Post build steps') {
      steps {
        writeFile(file: 'status.txt', text: 'It is fonderful!!')
      }
    }

  }
}