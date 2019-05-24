@Library('shared_library')_

node {

try{

 stage('Job Started Notification'){
      emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
   
   stage('Initialization') { 
       
    // Get some code from a GitHub repository
   git 'https://github.com/RekhaPrathap/MVC.git'
      
    // Get the Maven tool
    mvnHome = tool 'Maven_HOME'
    
      
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
    
    
    stage("SonarQube Quality Gate pass") { 
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
    
    stage('Job Success Notification'){
      emailext (
      subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    }
    
    catch(e){
    
    stage('Job Failure Notification'){
      emailext (
      subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
      )
    }
    
   }

}

}
