uuid-java [![Build Status](http://build.mc-api.net/buildStatus/icon?job=uuid-java)](http://build.mc-api.net/job/uuid-java/)
=========

A Java implementation of the [mc-api.net](http://mc-api.net) API.

## Implementation

Usage examples:

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

**Errors**

If the given username or UUID is invalid, `NULL` would be returned!

### Compilation

To compile just run ``mvn clean package`` or download the latest build from [build.mc-api.net](http://build.mc-api.net)

### Development

Prerequisites:
- [Lombok](http://projectlombok.org/)
