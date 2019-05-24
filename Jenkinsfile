@Library('shared_library')_
	

	node  {
	   
		
		try{

 stage('Send Job Started Notification'){
      emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    
	   
	   stage('initialization') { 
	   initialization "Maven_HOME", "https://github.com/RekhaPrathap/MVC.git"
	   }
	       
	   
	      
	    // Sonar cloud properties
	    //sonarCloudProperties = '-Dsonar.projectKey=RekhaPrathap_MVC -Dsonar.organization=rekhaprathap-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=ae0fc3ea0e7b5b3fa0cdf8b2cff85756cdaed572'
	      
	    // SonarQube properties
	  //  sonarQubeProperties = '-Dsonar.host.url=http://23.100.87.70:9000'
	  // }
	  
	    
	    stage('Quality check with SonarQube'){
	    sonarQube "sonar", "Maven_HOME"
	       
	    }
	    
	    
	    stage("SonarQube Quality Gate") { 
	    qualityGate "sonar"
	    }
	             
	    stage('Build docker image for war file'){
	        dockerImage "docker build -t rekha/project:${BUILD_NUMBER} ."
	    }
	    
	    stage('Deploy Artifacts'){
	        deployArtifacts "artifactory", "./target/*.war", "localsnapshot"
	    }
			stage('Send Job Success Notification'){
      emailext (
      subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    }
    
    catch(e){
    
    stage('Send Job Failure Notification'){
      emailext (
      subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
      )
    }
    }
	    }
