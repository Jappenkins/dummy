package org.myorg.utils

class ScanUtils implements Serializable {
    def steps

    ScanUtils(steps) {
        this.steps = steps
    }

    def securityScan() {
        steps.echo "🔍 Running security scan..."
        steps.sh 'echo Simulated security scan'
    }
}
