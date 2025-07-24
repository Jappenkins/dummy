def call(Map config = [:]) {
    def env = config.get('env', 'staging')
    echo "Deploying to ${env}"
    org.myorg.utils.DeployUtils.deployApp(env)
}
