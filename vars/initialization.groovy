    
def call(String mavenHome, String gitURL){

    // Connect GitHub repository
    git "${gitURL}"
      
    // Get the Maven tool
    mvnHome = tool "${mavenHome}"
    
   
}
