def call(Map config = [:]) {
    echo "Running tests for ${config.get('appName', 'MyApp')}"
    org.myorg.utils.TestUtils.runTests()
}
