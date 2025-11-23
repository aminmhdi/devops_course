pipeline {
    agent any
    tools {
        maven 'maven_tool'
    }
    stages {
        stage('CloneCode') {
            steps {
                git 'https://github.com/Sonal0409/DevOpsCodeDemo.git'
            }
        }
        stage('PackageCode') {
            steps {
                sh 'mvn package'
                script {
                    timeout(time: 10, unit: 'MINUTES') {
                        input(id: 'DeployGate', message: 'Continue to Deploy')
                    // button with name proceed and abort
                    }
                }
            }
        }
        stage('DeployCode') {
            steps {
                echo 'Deployment Completed'
            }
        }
    }
}
