Instagram for Java
==============
Java wrapper for Instagram's API v1

### Maven 
```xml
<dependency>
    <groupId>com.github.sola92</groupId>
    <artifactId>instagram-java</artifactId>
    <version>1.3.3</version>
</dependency>
```

### Authentication
 Firstly, build the authorization URL
```java
InstagramAuthentication auth =  new InstagramAuthentication();
String authUrl = auth.setRedirectUri("your_redirect_url")
                     .setClientSecret("your_app_secrect")
                     .setClientId("your_client_id")
                     .setScope("comments+likes")
                     .getAuthorizationUri();
```
 After the user has authorized the app, get the access token by passing the code given in the callback URL. 
```java
  AccessToken token = auth.build("code");
```
 Create the session using the access token and you're all set
```java 
  InstagramSession session = new InstagramSession(token);
  User rihanna = session.searchUsersByName("badgalriri").get(0);
``` 
### Endpoint Examples
Here are some common endpoint calls. Please refer to the javadoc at `/doc/com/sola/instagram/InstgramSession.html` for the full documentation of the endpoints.

#### Get basic information about a user 
```java
  //Endpoint: GET /users/3
  User user = session.getUserById(3);
```

#### See the authenticated user's feed, results are paginated 
```java
  //Endpoint: GET /users/self/feed
  PaginatedCollection<Media> feed = session.getFeed(); 
  for(Media media: feed) {
    //do stuff
  }
```

#### Get a list of the most recent media published by a user
```java
  //Endpoint: GET /users/3/media/recent
  int userId = 3;
  PaginatedCollection<Media> recentMedia = session.getRecentPublishedMedia(userId);
  for(Media media: recentMedia) {
    //do stuff
  }  
```

#### Get a media object
```java
  //Endpoint: GET /media/5233810105500317233
  Media media = session.getMedia("523381010550031723");
  //check for video
  if (media instanceof VideoMedia) { 
    VideoMedia video = (VideoMedia)media;
  }  
```

#### Search for a user by name
```java
  //Endpoint: GET /users/search?q=jack
  List<User> searchResults = session.searchUsersByName("jack");
```

#### Get a list of the user's followers and follows
```java 
  int userId = 3;
  // GET /users/3/follows
  PaginatedCollection<User> follows = session.getFollows(userId); 
  // GET /users/3/followed-by
  PaginatedCollection<User> followers = session.getFollowers(userId); 
```

#### Follow a user
```java 
  int targetUserId = 3;
  // POST /users/3/relationship
  session.modifyRelationship(targetUserId, Relationship.Action.FOLLOW)
```

#### Unfollow a user
```java 
  int targetUserId = 3;
  // POST /users/3/relationship
  session.modifyRelationship(targetUserId, Relationship.Action.UNFOLLOW)
```
## License
Copyright (c) 2013 Sola Ogunsakin
Licensed under the MIT license.

## About me
* Email : juniorsola@yahoo.com
* LinkedIn : http://ca.linkedin.com/pub/sola-ogunsakin/45/a5/5a0/
* Twitter : @SolaOgunsakin
