![Maintenance](https://img.shields.io/maintenance/yes/2023?logo=github&style=flat)
# JValorant

JValorant is an easy to use library to interact with the Riot-Valorant API
#### Version 0.1
- Note: The library is not fully complete but you can find most of the stuff you are looking for


# How to use JValorant ?

## First include the library
- You can use gradle or maven

### Gradle
``` gradle
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- Add the dependency
```gradle
dependencies {
	    implementation 'com.github.fero1xd:JValorant:0.1'
	}
```


### Maven
- Add the repository in pom.xml file
```maven
    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

```
- Add the dependency
```maven 

	<dependency>
	    <groupId>com.github.fero1xd</groupId>
	    <artifactId>JValorant</artifactId>
	    <version>0.1</version>
	</dependency>


```

### Jar
```
Use the Releases Section
```

## Quick Start
- Get Account data
```java
        JValorant api = new JValorant();

        try {
            AccountDataResult response = api.getAccountData("Fero", "STUCK");
            AccountData accountData = response.getAccountData();

            System.out.println(accountData.toString());
        } catch (ApiError error) {
            System.out.println(error.getResponse().getCode());
            System.out.println(error.getResponse().getHttpError());
            System.out.println(error.getResponse().getRawResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
```

- Get match history of a player
```java
        try {
            MatchHandler matchHandler = api.getMatchHandler();

            GetMatchHistoryResult matchHistory = matchHandler.getMatchHistory("Fero", "STUCK", Region.AP, MatchFilter.ESCALATION);
            Match[] matches = matchHistory.getMatches();

            for(Match m : matches) {
                System.out.println(m.metadata().mode());
            }

        } catch (ApiError error) {
            System.out.println(error.getResponse().getCode());
            System.out.println(error.getResponse().getHttpError());
            System.out.println(error.getResponse().getRawResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
```

- Get Leaderboard of specific region
```java
        try {
            LeaderboardHandler leaderboardHandler = api.getLeaderboardHandler();
    
            GetLeaderboardResult leaderboard = leaderboardHandler.getLeaderboard(Region.AP);
            LeaderboardUser[] leaderboardUsers = leaderboard.getLeaderboardUsers();
    
            for(LeaderboardUser u : leaderboardUsers) {
                System.out.println(u.leaderboardRank());
            }
        } catch (ApiError error) {
            System.out.println(error.getResponse().getCode());
            System.out.println(error.getResponse().getHttpError());
            System.out.println(error.getResponse().getRawResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
```
## Handler Structure

| Handler             | Description                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Leaderboard Handler | Use this to interact with the in game leaderboard |
| Match Handler | Use this when you want to do anything regarding matches |
| MMR Handler | Use this to get mmr info of players |


## Enums
| Enums        | Values                                                                     |
|--------------|----------------------------------------------------------------------------|
| Region       | ap, na, eu, kr, latam, br                                                  |
| Match Filter | none, escalation, spikerush, deathmatch, competitive, unrated, replication |
| Error        | bad_request, rate_limited, server_error, not_found                         |

## Api Used
[henrikdev](https://docs.henrikdev.xyz/valorant.html)

