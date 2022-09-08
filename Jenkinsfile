

pipeline {
    agent any
    environment {
        ANSIBLE_SERVER = "167.99.136.157"
        IMAGE_REPO = "praharlokhande/angularapp:1.1"

    }
    stages {
        
        stage("build App") {
            steps {
                script {
                    echo "build app"
                }
            }
        }
       
      stage('Docker Push') {
              
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            echo '$env.dockerHubUser'
            sh 'docker build -t ${IMAGE_REPO} .'
            sh'echo "$dockerHubPassword" | docker login --username "$dockerHubUser" --password-stdin'
          //sh 'docker login -u $env.dockerHubUser -p $env.dockerHubPassword'
            sh 'docker push ${IMAGE_REPO}'
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
                    echo 'deploying docker image...'
                    sshagent(credentials: ['ssh_mypc']) {
                        sh 'envsubst < kubernetes/secrets.yaml | kubectl apply -f -'
                        sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
                        sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
                      }
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
