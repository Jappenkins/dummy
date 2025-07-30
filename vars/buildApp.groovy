def call() {
    echo "Building the app for environment: ${params.ENVIRONMENT}"
    echo "Run tests: ${params.RUN_TESTS}"
    echo "Using deployment strategy: ${params.DEPLOY_STRATEGY}"
    // Your build logic goes here
}
