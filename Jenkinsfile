def project = "Web Testing"
pipeline{
    agent any
    parameters{
        string(name: "Repositories", defaultValue: "", trim: true, description: "Please enter your Repositories")
        choice(name: "Browser", choices: ["chrome", "edge"], description: "Please select a browser")
        string(name: "Tags", defaultValue: "", trim: true, description: "Please enter the desired tags")
    }
    stages{
        stage('Build'){
            steps{
                echo 'Hi, i am going to do testing!'
            }
        }
        stage('Testing'){
            steps{
                git "$params.Repositories"
                script{
                    bat """
                    mvn clean test -Dbrowser=$Browser -Dcucumber.filter.tags="$Tags"
                    """
                    }
            }
        }
        stage('Publish Report'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}