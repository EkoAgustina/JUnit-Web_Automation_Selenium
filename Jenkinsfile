pipeline{
    agent any
    tools {
        allure "allure-commandline"
        maven "maven3.8.4"
    }
    parameters{
        string(name: "Repositories", defaultValue: "", trim: true, description: "Please enter your Repositories")
        string(name: "Branch", defaultValue: "", trim: true, description: "Please enter your Branch")
        string(name: "Port", defaultValue: "", trim: true, description: "Please enter Port")
        string(name: "Tags", defaultValue: "@", trim: true, description: "Please enter the desired tags")
    }
    stages{
        stage('Project preparation'){
            steps{
                git branch: '$Branch', url: '$Repositories'
                echo '------------------------>Install a package and it\'s dependencies<-----------------------'
            }
        }
        stage('Build containers'){
            steps{
                echo '------------------->Builds and starts containers for a service defined<-------------------'
                script{
                    sh 'docker-compose up -d'
                    sleep(time: 1, unit: "SECONDS")
                }
            }
        }
        stage('Test'){
            steps{
                echo '------------------------------------>Running test<------------------------------------'
                script{
                    sleep(time: 1, unit: "SECONDS")
                    sh '''
                    mvn clean test -Dbrowser="$Port" -Dcucumber.filter.tags="$Tags"
                    '''
                }
            }
        }
        stage('Publish Report'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
    post {
        always {
            echo '--------------------->Stops and removes containers for a service defined<---------------------'
            script{
                sh 'docker-compose down'
                sleep(time: 1, unit: "SECONDS")
            }
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
    }
}