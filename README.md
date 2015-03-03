Idealab proof of concept with Spring Boot and Openshift. 

#### Run it
To build the application you can execute in command line this statement:  

```mvn clean package -DskipTests=true dbunit:operation spring-boot:run```

This will clean the project (delete target directory if exists), create H2 file database, populate it with some test data and run the Spring Boot application using embedded Tomcat.

#### Use it
If everything works fine then you should be able to navigate to ```http:\\localhost:8080\``` where you'll get the list of endpoints as a JSON Object. 

Application currently uses OAuth to authenticate access to protected endpoints like `\ideas`. Therefore, if you do `curl http://localhost:8080/ideas` you should get

```{"error":"unauthorized","error_description":"Full authentication is required to access this resource"}```

Therefore in order to access this resource you need ouath authentication token. There are some mock-details like client ID being `clientapp` and client secret being `123456` and the username is `user` and the password is... none :). Therefore issuing request like this

```curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=user&password&grant_type=password&scope=read&client_secret=123456&client_id=clientapp"```

should return

      {
        "access_token":"e9ba821b-b566-4980-bbcb-c51df6dce494",
        "token_type":"bearer",
        "refresh_token":"bfbcee41-25a3-4530-817d-eaca38d3ff85",
        "expires_in":43199,
        "scope":"read"
      }

Then, you can use 

```curl http://localhost:8080/ideas -H "Authorization: Bearer e9ba821b-b566-4980-bbcb-c51df6dce494"```

to obtain the list

      {
        "_links" : {
          "self" : {
            "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas{?page,size,sort}",
            "templated" : true
          }
        },
        "_embedded" : {
          "ideas" : [ {
            "name" : "Take over the world",
            "description" : "Go on and take it!",
            "created" : "2015-01-01T14:00:00.000+0000",
            "modified" : null,
            "_links" : {
              "self" : {
                "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas/1"
              },
              "ratings" : {
                "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas/1/ratings"
              },
              "artifact" : {
                "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas/1/artifact"
              },
              "owner" : {
                "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas/1/owner"
              },
              "state" : {
                "href" : "http://idealabspring-ernicommunity.rhcloud.com/ideas/1/state"
              }
            }
          } ]
        },
        "page" : {
          "size" : 20,
          "totalElements" : 1,
          "totalPages" : 1,
          "number" : 0
        }
      }

After some period of time the access_token should expire, so you can get renewal using refresh_token:

```curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "grant_type=refresh_token&refresh_token=<<REFRESH_TOKEN>>&client_secret=123456&client_id=clientapp"```

#### Tweak it
- this project uses Project Lombok library, so you should have IDE that supports it (Eclipse, IntelliJ IDEA). More information can be found here: http://projectlombok.org/. 
- the Java version is 7, but version 8 is supported too (however not by OpenShift)
- the application can be deployed to OpenShift (see ```.openshift``` directory)
