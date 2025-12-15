pipeline {
  agent any

  tools {
    jdk 'JDK17'     // <-- ADD THIS (name must match Manage Jenkins -> Tools)
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Show Java/Maven versions') {
      steps {
        script {
          if (isUnix()) {
            sh 'java -version && mvn -version && which java && which mvn'
          } else {
            bat 'java -version'
            bat 'mvn -version'
            bat 'where java'
            bat 'where mvn'
          }
        }
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
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
      archiveArtifacts artifacts: 'target/**, test-output/**', allowEmptyArchive: true
    }
  }
}
