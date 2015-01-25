uuid-java [![Build Status](http://build.mc-api.net/buildStatus/icon?job=uuid-java)](http://build.mc-api.net/job/uuid-java/)
=========

A Java implementation of the [mc-api.net](https://mc-api.net) API.

## Custom Handlers

A handler decides how a uuid or username is obtained. The default handler will just query the API and then cache the result. However you can create your own, custom handler.

There are two premade for [Bukkit](https://bukkit.org)/[Spigot](http://spigotmc.org) and [BungeeCord](https://github.com/spigotmc/bungeecord):
- [Bukkit Handler](https://gist.github.com/njb-said/cec2f56907e8eaa54021): Checks if player is online or has played before, before querying API
- [Bungee Handler](https://gist.github.com/njb-said/39e1b0fdebc18b02fc14): Checks if player is online before querying API

Just implement these somewhere within your project and then set it as the active handler using this example (example is for the BungeeHandler.
```java
UUIDAPI.setHandler(new BungeeHandler());
```

**We suggest you put it in the package:** `net.mcapi.uuid.handlers`

## Implementation

**Setting request server**

MC-API has multiple mirrors, you can choose your mirror like so:
```java
UUIDAPI.setRegion(ServerRegion)
```
A list of mirrors can be found [here](https://github.com/MC-API/static#mirrors). The default mirror is set to US.

### Usage examples:

**Username to UUID**

```java
UUID uuid = UUIDAPI.getUUID("AeroPvP");
```

Would return the UUID object for AeroPvP.

You can also get a string alternative (which does not contain hyphens)

```java
String uuid = UUIDAPI.getUUIDString("AeroPvP");
```

**UUID to Username**

```java
String playerName = UUIDAPI.getUsername("a8889e3068b84ce8963f4ea259c3ebe3");
```

Would return:

`njb_said`

### Errors

If the given username or UUID is invalid, `NULL` would be returned!

### Compilation

To compile just run ``mvn clean package`` or download the latest build from [build.mc-api.net](http://build.mc-api.net)

### Maven Repository

There is a maven repository at [build.mc-api.net/plugin/repository/everything](http://build.mc-api.net/plugin/repository/everything)

Maven Configuration:

```xml
    <repositories>
        <repository>
            <id>mcapi</id>
            <url>http://build.mc-api.net/plugin/repository/everything/</url>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>net.mcapi.uuid</groupId>
            <artifactId>uuid-java</artifactId>
            <version>1.0.6</version>
        </dependency>
    </dependencies>
```
