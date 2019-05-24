def call(String sonarServer, String mavenHome){

        mvnHome = tool "${mavenHome}"
        withSonarQubeEnv("${sonarServer}"){
        
            sh "${mvnHome}/bin/mvn clean package sonar:sonar"
    
        }    
}
