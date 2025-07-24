package org.myorg.utils

class DeployUtils implements Serializable {
    def steps

    DeployUtils(steps) {
        this.steps = steps
    }

    def deployApp(String env) {
        steps.echo "Deploying application to ${env} environment"
        steps.sleep 1
        steps.echo "Deployment to ${env} completed."
    }
}
