def call(String artifactoryServerName, String source, String destination){

 def SERVER_ID = "${artifactoryServerName}" 
        def server = Artifactory.server SERVER_ID
        def downloadFile = 
        """
        {
        "files": [
            {
                "pattern": "${source}",
                "target": "./${destination}/"
            }
        ]
        }
        """
        def buildInfo = Artifactory.newBuildInfo() 
        buildInfo=server.download(downloadFile) 
        server.publishBuildInfo(buildInfo)
        

}
