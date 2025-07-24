def call(Map config = [:]) {
    pipeline {
        agent any
        parameters {
            choice(name: 'ENVIRONMENT', choices: ['dev', 'staging', 'prod'], description: 'Select environment')
        }
        stages {
            stage('Setup') {
                steps {
                    script {
                        def buildUtils = new org.myorg.utils.BuildUtils(this)
                        buildUtils.setupJava()
                    }
                }
            }
            stage('Install Dependencies') {
                steps {
                    script {
                        def buildUtils = new org.myorg.utils.BuildUtils(this)
                        buildUtils.installDependencies()
                    }
                }
            }
            stage('Build') {
                steps {
                    script {
                        def buildUtils = new org.myorg.utils.BuildUtils(this)
                        buildUtils.buildApp()
                    }
                }
            }
            stage('Test') {
                steps {
                    script {
                        def testUtils = new org.myorg.utils.TestUtils(this)
                        testUtils.runTests()
                    }
                }
            }
            stage('Deploy') {
                steps {
                    script {
                        def deployUtils = new org.myorg.utils.DeployUtils(this)
                        deployUtils.deployApp(params.ENVIRONMENT)
                    }
                }
            }
        }
    }
}
