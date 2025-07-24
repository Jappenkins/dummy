def call(Map config = [:]) {
    def appName = config.get('appName', 'MyApp')
    echo "Building ${appName}"
    org.myorg.utils.BuildUtils.setupJava()
    org.myorg.utils.BuildUtils.installDependencies()
    org.myorg.utils.BuildUtils.buildApp()
}
