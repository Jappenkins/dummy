import groovy.sql.Sql

def call(Map params) {
    def dbDriver = Class.forName(params.driverClass).newInstance()
    Map dbConnParams = [
        url: params.dbUrl,
        user: params.dbUser,
        password: params.dbPass,
        driver: params.driverClass
    ]

    echo "✅ JDBC driver class loaded: ${params.driverClass}"
    def sql = Sql.newInstance(dbConnParams)
    try {
        sql.executeInsert("""
            INSERT INTO pipeline_run_history (
                BUILD_NAME, BUILD_STATUS, FAILED_STAGE, APP_NAME, BRANCH_NAME,
                FAILURE_REASON, ENVIRONMENT, PIPELINE_TYPE, TIME,
                SCA_SCAN, SAST_SCAN, DAST_SCAN
            ) VALUES (
                ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
            )
        """, [
            params.buildName,
            params.buildStatus,
            params.failedStage,
            params.appName,
            params.branchName,
            params.failureReason,
            params.environment,
            params.pipelineType,
            java.sql.Timestamp.valueOf(java.time.LocalDateTime.now(java.time.ZoneId.of("Asia/Kuwait"))),
            params.scaScan.toBoolean(),
            params.sastScan.toBoolean(),
            params.dastScan.toBoolean()
        ])
        echo "✅ Record inserted into SQL Server."
    } finally {
        sql.close()
    }
}
