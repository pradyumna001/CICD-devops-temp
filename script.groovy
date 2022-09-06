
def buildApp(){
        echo 'Building the application...'
       
                    sh 'npm install -g'
                    sh 'npm run build'
                   
           

}

def testApp(){
        echo 'Testing the application...'
       
        sh 'npm run test'

}
def publishApp(){
        environment {
            IMAGE_REPO = "praharlokhande/AngularApp"
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
   
         
                    echo 'deploying docker image...'
                    sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
                    sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
          
}

return this

