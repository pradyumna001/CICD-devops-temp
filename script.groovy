
def buildApp(){
        echo 'Building the application...'

}

def testApp(){
        echo 'Testing the application...'

}
def publishApp(){
        environment {
            IMAGE_REPO = "praharlokhande/java-maven-app"
        }
        echo 'Publishing the application...'
        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t praharlokhande/AngularApp:app-1.0 ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push praharlokhande/AngularApp:app-1.0"
                    }
}
def deployApp() {
    echo 'deploying the application...'
    // environment {
    //             AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
    //             AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
    //             APP_NAME = 'AngularApp'
    //         }
            steps {
                script {
                    echo 'deploying docker image...'
                    sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
                    sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
                }
            }
}

return this

