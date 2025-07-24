package org.myorg.utils

class BuildUtils implements Serializable {
    def steps

    BuildUtils(steps) {
        this.steps = steps
    }

    def setupJava() {
        steps.echo "🔧 Setting up Java..."
        steps.sh 'echo Simulated Java setup'
    }

    def installDependencies() {
        steps.echo "📦 Installing dependencies..."
        steps.sh 'echo Simulated dependency installation'
    }

    def buildApp() {
        steps.echo "🏗️ Building application..."
        steps.sh 'echo Simulated app build'
    }
}
