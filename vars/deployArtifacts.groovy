def call(String artifactoryServerName, String artifactDeployed, String repo){
        echo 'Deploying artifacts to Artifactory..'
        def SERVER_ID = "${artifactoryServerName}" 
        def server = Artifactory.server SERVER_ID
        def uploadFile = 
        """
        {
        "files": [
            {
                "pattern": "${artifactDeployed}",
                "target": "${repo}/${env.BUILD_NUMBER}/"
            }
        ]
        }
        """
        def buildInfo = Artifactory.newBuildInfo() 
        buildInfo=server.upload(uploadFile) 
        server.publishBuildInfo(buildInfo)
        echo "Artifacts deployed to artifactory!"
    }
