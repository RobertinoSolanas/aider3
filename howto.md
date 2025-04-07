# Getting Started with MicroK8s and Podman Registry

## Reference Documentation
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

## MicroK8s and Podman Registry Configuration Guide

This guide explains how to configure MicroK8s' local registry to work with Podman for local Kubernetes development.

## 1. Enable MicroK8s Registry

```bash
microk8s enable registry
```

This starts a local container registry on port 32000.

## 2. Configure Podman to Trust the Registry

Edit `/etc/containers/registries.conf` (system-wide) or `~/.config/containers/registries.conf` (user-specific) to add:

```ini
[[registry]]
location = "localhost:32000"
insecure = true
```

For immediate effect, you may need to restart Podman (if running as a service).

## 3. Build and Push Images

```bash
# Build your image with Podman
podman build -t localhost:32000/your-image-name .

# Push to MicroK8s registry
podman push localhost:32000/your-image-name
```

## 4. Configure Kubernetes Deployments

In your `deployment.yaml`, reference images as:

```yaml
image: localhost:32000/your-image-name:tag
```

## 5. Verify Setup

Check registry contents:
```bash
curl http://localhost:32000/v2/_catalog
```

Check Kubernetes can pull images:
```bash
microk8s kubectl describe pod your-pod-name | grep Image
```

## Troubleshooting

### Certificate Errors
If seeing TLS errors, ensure:
1. The registry is properly marked as insecure in registries.conf
2. You're using `localhost:32000` (not 127.0.0.1)

### Permission Issues
For system-wide Podman:
```bash
sudo chmod 644 /etc/containers/registries.conf
```

### Podman Push Fails
Try with explicit insecure flag:
```bash
podman push --tls-verify=false localhost:32000/your-image-name
```

## Notes
- The registry persists data in `/var/snap/microk8s/common/registry`
- Access from other machines requires additional network configuration

## Maven Parent Overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Quick Commands

```bash
# Build application
mvn clean package docker:build

# GIT
git add .
git commit -m "spring-boot"
git push
```
