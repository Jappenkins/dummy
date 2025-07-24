package org.myorg.utils
class ScanUtils {
    static def runSecurityScan() {
        echo "Running security scan"
        sh 'echo simulated security scan'
    }
}