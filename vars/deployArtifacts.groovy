def call(String artifactoryServerName, String artifact, String repo){
        echo 'inside artifact script'
        def SERVER_ID = "${artifactoryServerName}" 
        def server = Artifactory.server SERVER_ID
        def uploadFile = 
        """
        {
        "files": [
            {
                "pattern": "${artifact}",
                "target": "${repo}/${BUILD_NUMBER}/"
            }
        ]
        }
        """
        echo 'below pattern and target artifact script'
        def buildInfo = Artifactory.newBuildInfo() 
        buildInfo=server.upload(uploadFile) 
        server.publishBuildInfo(buildInfo)
    }
