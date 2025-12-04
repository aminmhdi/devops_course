pipeline {
    agent any

    environment {
        KUBECONFIG = '/var/lib/jenkins/.kube/config'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/aminmhdi/wordpress-k8s'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f mysql-pvc.yaml'
                sh 'kubectl apply -f mysql-deployment.yaml'
                sh 'kubectl apply -f mysql-service.yaml'
                sh 'kubectl apply -f wordpress-pvc.yaml'
                sh 'kubectl apply -f wordpress-deployment.yaml'
                sh 'kubectl apply -f wordpress-service.yaml'
            }
        }
    }

    post {
        success {
            echo 'WordPress successfully deployed!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}