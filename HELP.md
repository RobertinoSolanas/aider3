# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.3/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

# podman run -p 8080:8080 docker.io/library/aider3:latest
# source /home/rob/Documents/development/java/java_venv/bin/activate
# aider --model deepseek/deepseek-chat
# git add .
# git commit -m "spring-boot"
# git push
# mvn clean package docker:build
# ---- microk8s connect local registry ----
# 1 First, enable the registry 
# add-on:                                                                                        
                                                                                                                         
microk8s enable registry                                                                                                     
                                                                                                                             

This will:                                                                                                                   

 • Start a local container registry on port 32000                                                                            
 • Configure MicroK8s to use this registry                                                                                   
 • Set up the necessary Kubernetes resources                                                                                 

 2 Verify it's running:                                                                                                      

                                                                                                                             
microk8s kubectl get pods -n container-registry                                                                              
                                                                                              