package org.myorg.utils

class DeployUtils implements Serializable {
    def steps

    DeployUtils(steps) {
        this.steps = steps
    }

    def deployApp(env) {
        steps.echo "🚀 Deploying to ${env}..."
        steps.sh "echo Simulated deployment to ${env}"
    }
}
