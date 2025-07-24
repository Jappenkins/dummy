def call(Map config = [:]) {
    pipeline {
        agent any
        parameters {
            choice(name: 'DEPLOY_ENV', choices: ['staging', 'production'], description: 'Environment to deploy to')
            string(name:'Cleaned message', defaultValue: 'Cleaned message', description: 'Message to display')
            booleanParam(name: 'RUN_SECURITY_SCAN', defaultValue: true, description:'run security scan')
        }
        stages{
            stage ('Setup'){
                steps{
                    script{
                        echo "Setting up ${params.APP_NAME}"
                        org.myorg.utils.BuildUtils.setupJava()
                    }
                }
            }
            stage ('Install Dependencies'){
                steps{
                    script{
                        org.myorg.utils.BuildUtils.installDependencies()
                    }
                }
            }
            stage ('Build'){
                steps{
                    script{
                        org.myorg.utils.BuildUtils.buildApp()
                    }
                }
            }
            stage ('Test'){
                steps{
                    script{
                        org.myorg.utils.BuildUtils.runTests()
                    }
                }
            }
            stage ('Deploy'){
                steps{
                    script{
                        org.myorg.utils.BuildUtils.deployApp()
                    }
                }
            }
        }

        }
}