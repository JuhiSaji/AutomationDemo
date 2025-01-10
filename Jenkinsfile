pipeline {
    agent any

    stages {
         stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

              
    }

    post {
        success {
            emailext(
    subject: "Build Successful: ${BUILD_NUMBER}",
    body: "The build ${BUILD_NUMBER} was successful.\n\nSee details at: ${BUILD_URL}",
    to: 'maryjuhi0215@gmail.com'
)
        }
        failure {
           emailext(
    subject: "Build Failed: ${BUILD_NUMBER}",
    body: "The build ${BUILD_NUMBER} failed.\n\nSee details at: ${BUILD_URL}",
    to: 'maryjuhi0215@gmail.com'
)
        }
    }
}
