def call(String imageName){


//connect to docker hub
 docker.withRegistry('https://registry.hub.docker.com', 'dockerhubpush') {
    
    //build docker image
    image = docker.build("${imageName}:${BUILD_NUMBER}")
    
    //push image to hub
    image.push()
    
   }
}
