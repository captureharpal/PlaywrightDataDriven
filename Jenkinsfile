pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Install Playwright Browsers') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn -B -q -DskipTests exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
          } else {
            bat 'mvn -B -q -DskipTests exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
          }
        }
      }
    }
stage('Show Java/Maven versions') {
  steps {
    bat 'java -version'
    bat 'mvn -version'
    bat 'where java'
    bat 'where mvn'
  }
}
    stage('Run Tests') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn -B -U clean test'
          } else {
            bat 'mvn -B -U clean test'
          }
        }
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/**, test-output/**', allowEmptyArchive: true
    }
  }
}
