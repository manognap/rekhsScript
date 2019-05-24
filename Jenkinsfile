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
    
   stage('Setup and initialization') { 
       setup "Maven_HOME", "https://github.com/RekhaPrathap/MVC.git"
   }
    
    stage('Quality check with SonarQube'){
      sonarQube "sonar", "Maven_HOME"
    }
    
    stage("SonarQube Quality Gate") { 
       sonarQualityGate "sonar"
    }
    
    stage('Build docker image for war file'){
       createDockerImage "rekha/project"
    }
    
    stage('Deploy Artifacts'){
        deployArtifacts "artifactory", "./target/*.war", "local-snapshot"
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
