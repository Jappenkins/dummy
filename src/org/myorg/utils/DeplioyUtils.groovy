package org.myorg.utils
class DeployUtils {
    static def deployApp() {
        echo "Deploying app to ${params.DEPLOY_ENV}"
        sh 'echo simulated deployment to ${params.DEPLOY_ENV}'
    }
}