pipeline {
    agent {
        label 'ec2-univesp'
    }
    options {
        disableConcurrentBuilds()
        parallelsAlwaysFailFast()
        timeout(time: 1, unit: 'HOURS')
    }
    stages {
        stage("Deploy") { steps { script {
            sh """
                echo 'oi'
            """
        } } }
    }
    post {
        always { cleanWs() }
    }
}