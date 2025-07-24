def call(Map config = [:]) {
    def scanEnabled = config.get('runScan', true)
    if (scanEnabled) {
        echo "Running security scan"
        org.myorg.utils.ScanUtils.securityScan()
    } else {
        echo "Skipping security scan"
    }
}
