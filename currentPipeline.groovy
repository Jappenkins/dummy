def call(Map config = [:]) {
    pipeline {
        agent any
        parameters {
            choice(name: 'DEPLOY_ENV', choices: ['staging', 'production'], description: 'Target environment')
            string(name: 'APP_NAME', defaultValue: 'DemoApp', description: 'Application name')
            booleanParam(name: 'RUN_SECURITY_SCAN', defaultValue: true, description: 'Enable security scan')
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
            stage('Security Scan') {
                when {
                    expression { return params.RUN_SECURITY_SCAN }
                }
                steps {
                    script {
                        def scanUtils = new org.myorg.utils.ScanUtils(this)
                        scanUtils.securityScan()
                    }
                }
            }
            stage('Deploy') {
                steps {
                    script {
                        def deployUtils = new org.myorg.utils.DeployUtils(this)
                        deployUtils.deployApp(params.DEPLOY_ENV)
                    }
                }
            }
        }
    }
}
