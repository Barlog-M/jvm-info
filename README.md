Simple JVM REST Info
=====================

Simple app with one endpoint `/info` with info about JVM for study docker 

```
docker run -d -p 8080:8080 --name info --cpuset-cpus=0,1 --memory=256M info
```

### Run
```
gradle run
```

### Create executable jar with dependencies
```
gradle clean shadowJar
```
