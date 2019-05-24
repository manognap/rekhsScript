@Library('shared_library')_

node {
   def mvnHome
   def sonarCloudProperties
   def sonarQubeProperties
   
   stage('Setup and initialization') { 
       
    // Get some code from a GitHub repository
   git 'https://github.com/RekhaPrathap/MVC.git'
      
    // Get the Maven tool
    mvnHome = tool 'MAVEN_HOME'
    
      
    // Sonar cloud properties
    sonarCloudProperties = '-Dsonar.projectKey=RekhaPrathap_MVC -Dsonar.organization=rekhaprathap-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=ae0fc3ea0e7b5b3fa0cdf8b2cff85756cdaed572'
      
    // SonarQube properties
    sonarQubeProperties = '-Dsonar.host.url=http://23.100.87.70:9000'
   }
  
    
    stage('Quality check with SonarQube'){
        withSonarQubeEnv('sonar'){
        
            sh "'${mvnHome}/bin/mvn' clean package sonar:sonar"
    
        }    
    }
    
    
    stage("SonarQube Quality Gate") { 
                withSonarQubeEnv('sonar'){
        timeout(time: 1, unit: 'MINUTES') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
        }
        }
    }
    
    stage('Build docker image for war file'){
        sh "docker build -t rekha/project:${BUILD_NUMBER} ."
    }
    
    stage('Deploy Artifacts'){
        deployArtifacts "artifactory", "./target/*.war", "localsnapshot"
    }

}
