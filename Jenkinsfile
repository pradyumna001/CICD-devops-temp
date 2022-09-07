

pipeline {
    agent any
    stages {
        
        stage("build App") {
            steps {
                script {
                    echo "build app"
                }
            }
        }
        
        stage("Test App") {
            steps {
                script {
                    echo "Test app"
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    
                    echo "build and push image"

                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploy app"
                }
            }
        }
    }
}
