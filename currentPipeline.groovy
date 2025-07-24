pipeline {
    agent any

    parameters {
        choice(name: 'DEPLOY_ENV', choices: ['staging', 'production'], description: 'Choose the environment to deploy to')
        string(name: 'APP_NAME', defaultValue: 'DemoApp', description: 'Enter the application name')
        booleanParam(name: 'RUN_SECURITY_SCAN', defaultValue: true, description: 'Enable Security Scanning?')
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
        MAVEN_HOME = '/usr/share/maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }

    options {
        timestamps()
    }

    stages {
        stage('Initialize') {
            steps {
                echo "🛠️ Starting pipeline for ${params.APP_NAME} on ${params.DEPLOY_ENV}"
            }
        }

        stage('Setup Java') {
            steps {
                echo "☕ Setting up Java..."
                sh '''
if command -v java > /dev/null; then
  java -version
else
  echo "Java not found (simulated)"
fi
'''
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    installDependencies()
                }
            }
        }

        stage('Code Linting') {
            steps {
                echo "🔍 Running code lint checks..."
                sh 'echo Simulating lint check...'
            }
        }

        stage('Build') {
            steps {
                script {
                    buildApp()
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    runTests()
                }
            }
        }

        stage('Security Scan') {
            when {
                expression { return params.RUN_SECURITY_SCAN }
            }
            steps {
                echo "🔒 Running security scans..."
                sh 'echo Simulating security scan...'
            }
        }

        stage('Docker Build') {
            steps {
                echo "🐳 Building Docker image..."
                sh "echo docker build -t ${params.APP_NAME}:${BUILD_NUMBER} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "📦 Pushing Docker image..."
                sh "echo docker push registry.example.com/${params.APP_NAME}:${BUILD_NUMBER}"
            }
        }

        stage('Deploy') {
            steps {
                script {
                    deployToEnvironment(params.DEPLOY_ENV)
                }
            }
        }

        stage('Smoke Tests') {
            steps {
                echo "🚦 Running smoke tests..."
                sh 'echo Simulating smoke test...'
            }
        }

        stage('Notify') {
            steps {
                echo "📣 Notifying team..."
                sh 'echo Simulating Slack/email notification...'
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully."
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}

// ---------- INLINE FUNCTIONS ----------

def installDependencies() {
    echo "📦 Installing dependencies..."
    sh 'echo Simulating mvn install...'
}

def buildApp() {
    echo "🏗️ Building the application..."
    sh 'echo Simulating mvn package...'
}

def runTests() {
    echo "🧪 Running unit tests..."
    sh 'echo Simulating mvn test...'
}

def deployToEnvironment(env) {
    echo "🚀 Deploying ${params.APP_NAME} to ${env} environment..."
    sh "echo Simulating deployment to ${env}..."
}
