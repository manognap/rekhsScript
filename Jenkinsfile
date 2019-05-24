@Library('shared_library')_
	

	node (label:'salve_jenkins') {
	   
	   
	   stage('Setup and initialization') { 
	   setup "Maven_HOME", "https://github.com/RekhaPrathap/MVC.git"
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
	    sonarQualityGate "sonar"
	    }
	             
	    stage('Build docker image for war file'){
	        sh "docker build -t rekha/project:${BUILD_NUMBER} ."
	    }
	    
	    stage('Deploy Artifacts'){
	        deployArtifacts "artifactory", "./target/*.war", "localsnapshot"
	    }
	    }
