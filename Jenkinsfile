pipeline {
    agent any

    stages {

        stage ('Build Docker Image') {
            steps {
                script {
                    dockerapp = docker.build("jehmes/teste-automatizados:${env.BUILD_ID}", "--file .teste-automatizados/src/Dockerfile ./src")
                }
            }
        }

    }
}