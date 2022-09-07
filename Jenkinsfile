

pipeline {
    agent any
    environment {
        ANSIBLE_SERVER = "167.99.136.157"
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
      agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            echo "${env.dockerHubUser}"
            sh "docker build -t ${env.dockerHubUser}/AngularApp:1.0 ."
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh "docker push ${env.dockerHubUser}/AngularApp:1.0"
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
