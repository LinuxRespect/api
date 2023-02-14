pipeline {
  agent any
  stages {
    stage('Log tool version') {
      parallel {
        stage('Log tool version') {
          steps {
            sh '''mvn --version
git -- version
java -version'''
          }
        }

        stage('Check for pom') {
          steps {
            fileExists 'pom.xml'
          }
        }

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