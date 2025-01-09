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

        stage('Publish Test Results') {
            steps {
               publishTestNGResults testResults: '**/test-output/testng-results.xml'   // For TestNG test reports

            }
        }

        
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
