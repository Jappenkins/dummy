package org.myorg.utils
class BuildUtils {
    static def setupJava() {
        steps.echo "Setting up Java"
        steps.sh 'echo simulated java setup'

    }
    static def installDependencies() {
        steps.echo "Installing dependencies"    
        steps.sh 'echo simulated dependency installation'
    }
    static def buildApp() {
        steps.echo "Building app"   
        steps.sh 'echo simulated build'
    }
}
