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
        stage("Build") { steps { script {
            sh """
                set +x
                source "/home/ubuntu/.sdkman/bin/sdkman-init.sh" > /dev/null 2>&1
                sdk use java 17.0.6-amzn
                sdk use maven 3.8.5
                set -x
                mvn clean
                mvn clean install
            """
        } } }
        stage("Deploy") { steps { script {
            sh """
                rsync -avz --delete ${WORKSPACE}/{Dockerfile,docker-compose.yaml,target} /home/ubuntu/board-games-back
                cd /home/ubuntu/board-games-back
                docker-compose build --no-cache
                docker-compose down
                docker-compose up -d
            """
        } } }
    }
    post {
        always { cleanWs() }
    }
}