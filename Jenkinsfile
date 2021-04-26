pipeline {
    agent any
    tools {
        nodejs 'node-14.16.1'
        jdk 'jdk-8'
        maven 'maven-4.0.0'
    }
    triggers {
        pollSCM('H/5 6-23 * * 1-6')
    }
    stages {
        stage('Build NPM front end') {
            steps {
                sh 'cd src/main/frontend && npm install'
                sh 'cd src/main/frontend && npm run-script build'
            }
        }
        stage('Build Java') {
            steps {
                sh 'mvn clean deploy'
            }
        }
    }
}