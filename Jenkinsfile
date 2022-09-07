
#!groovy


def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build App") {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        
        stage("Test App") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    
                    gv.publishApp();

                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
