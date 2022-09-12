

pipeline {
    agent any
    environment {
//         ANSIBLE_SERVER = "167.99.136.157"
        REPO_NAME = "praharlokhande"
        IMAGE_REPO = "${REPO_NAME}/angularapp:1.1"

    }
    stages {
//                 stage("build App") {
//             steps {
//                 script {
//                     echo "build app"
//                     sh "npm run build"
//                     sh "npm run build"
//                 }
//             }
//         }

       
      stage('Docker build and Push') {
              
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            powershell 'docker build -t praharlokhande/angularapp:1.1 .'
            powershell 'docker login --username praharlokhande  --password Pass@1234'
            powershell 'docker push praharlokhande/angularapp:1.1'
            
//             echo 'docker build and push'
            
//             sh 'docker build -t ${IMAGE_REPO} .'
//             sh'echo "$dockerHubPassword" | docker login --username "$dockerHubUser" --password-stdin'
//             //sh 'docker login -u $env.dockerHubUser -p $env.dockerHubPassword'
//             sh 'docker push ${IMAGE_REPO}'
       }
      }
      }
      
        stage("Test App") {
            steps {
                script {
                    echo "Testing application"
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    powershell 'kubectl apply -f kubernetes/deployment.yaml'
                    powershell 'kubectl apply -f kubernetes/service.yaml'
                    
//                     echo 'deploying docker image...'
//                     sh 'kubectl apply -f kubernetes/deployment.yaml'
//                     sh 'kubectl apply -f kubernetes/service.yaml'
//                         sh "kubectl create secret generic docker_secret --from-literal=username="$dockerHubUser" --from-literal=password="$dockerHubPassword""
//                         sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
//                         sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
                }
            }
        }
//         stage("copy files to ansible server") {
//             steps {
//                 script {
//                     echo "copying all neccessary files to ansible control node"
//                     sshagent(['ansible-server-key']) {
//                         sh "scp -o StrictHostKeyChecking=no ansible/* root@${ANSIBLE_SERVER}:/root"

//                         withCredentials([sshUserPrivateKey(credentialsId: 'ec2-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
//                             sh 'scp $keyfile root@$ANSIBLE_SERVER:/root/ssh-key.pem'
//                         }
//                     }
//                 }
//             }
//         }
//         stage("execute ansible playbook") {
//             steps {
//                 script {
//                     echo "calling ansible playbook to configure ec2 instances"
//                     def remote = [:]
//                     remote.name = "ansible-server"
//                     remote.host = ANSIBLE_SERVER
//                     remote.allowAnyHosts = true

//                     withCredentials([sshUserPrivateKey(credentialsId: 'ansible-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]){
//                         remote.user = user
//                         remote.identityFile = keyfile
//                         sshScript remote: remote, script: "prepare-ansible-server.sh"
//                         sshCommand remote: remote, command: "ansible-playbook my-playbook.yaml"
//                     }
//                 }
//             }
//         }
    }
}
