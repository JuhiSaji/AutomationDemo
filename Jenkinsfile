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
                subject: "Build Successful: ${env.JOB_NAME} ${env.BUILD_NUMBER}",
                body: "The build ${env.BUILD_NUMBER} was successful.\n\nSee details at: ${env.BUILD_URL}",
                to: 'maryjuhi0215@gmail.com'
            )
        }
        failure {
            emailext(
                subject: "Build Failed: ${env.JOB_NAME} ${env.BUILD_NUMBER}",
                body: "The build ${env.BUILD_NUMBER} failed.\n\nSee details at: ${env.BUILD_URL}",
                to: 'maryjuhi0215@gmail.com'
            )
        }
    }
}
