# user-directory-cassandra
User Directory with Cassandra

This is a User Directory template that contains fields such as name, phone number, and address. It includes connections to Apache Cassandra and uses a RESTful services.

Prerequisites to run program:
- Must have Docker installed on local machine to install Cassandra
- Recommend installing Postman to easily test RESTful calls

To begin (see Common Docker Commands for command explanations):
- After installing Docker, run Docker and pull Cassandra. Version as of this writing is 4.0.1.
  - docker pull cassandra:4.0.1
- inside IDE terminal, enter docker-compose up -d to run docker-compose.yml in detached mode which will create named docker container, and, since it's detached, will allow you to continue to use the IDE terminal.
- enter into the virtual terminal for the running container using docker exec -it command
- log into the cqlsh using command:
  - cqlsh 127.0.0.1 -u cassandra -p cassandra
  - use cqlsh to:
    - create keyspace
    - use keyspace
    - create table
    - insert data into table
- open Postman and test GET, POST, PUT, DELETE commands against populated table

Common Docker Commands
- docker pull <i><b>image</b></i>
  - installs docker image locally. Can add :<i><b>version</b></i> to specify a version. Otherwise, defaults to latest.
- docker images
  - Shows list of images that have been downloaded locally in Docker with version Tag
- docker ps 
  - provides list of running containers. Provides additional information about containers including container name and id, image name and ports.
- docker ps -a
  - provides history of containers whether running or not
- docker run <i><b>image</b></i> (ex: docker run cassandra)
  - creates a running container of the indicated image. Will also pull image from repository if it does not already exist locally.
- docker run -d <i><b>image</b></i> (ex: docker run -d cassandra)
  - creates a running container of the indicated image in detached mode. output = container id
- docker run -p####:#### <i><b>image</b></i> (ex: docker run -p9000:9042 cassandra)
  - creates a running container of the indicated image tied to particular ports (hostPort:containerPort)
- docker-compose up -d
  - starts up a container based on paramters within docker-compose.yml. -d runs in detached mode.
- docker stop <i><b>containerId</b></i> OR docker-compose stop
  - stops a container
- docker start <i><b>containerId</b></i>
  - starts a container that already exists.
- docker logs <i><b>id/name</b></i>
  - get the logs for a particular container. Can be retrieved by using container id or name.
- docker exec -it <i><b>containerId/name</b></i> /bin/bash
  -  it = interactive terminal. Get terminal of a running container. WIll put you in virtual file system within container as a root user.