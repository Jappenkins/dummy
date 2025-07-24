package org.myorg.utils

class TestUtils implements Serializable {
    def steps

    TestUtils(steps) {
        this.steps = steps
    }

    def runTests() {
        steps.echo "🧪 Running tests..."
        steps.sh 'echo Simulated test execution'
    }
}
