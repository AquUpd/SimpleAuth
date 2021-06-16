## Simple Authentication Mod
[<img src="https://i.imgur.com/Ol1Tcf8.png" alt="Requires Fabric API." width="200px" href="https://www.curseforge.com/minecraft/mc-mods/fabric-api">](https://www.curseforge.com/minecraft/mc-mods/fabric-api)


See [wiki](https://github.com/samolego/SimpleAuth/wiki) for more information.

## License
Libraries that the project is using:
- `Argon2 (LGPLv3)` https://github.com/phxql/argon2-jvm
- `BCrypt (Apache 2)` https://github.com/patrickfav/bcrypt
- `Bytes (Apache 2)` https://github.com/patrickfav/bytes-java
- `leveldb (BSD-3-Clause)` https://github.com/google/leveldb
- `JNA (Apache 2 || LGPLv3)` https://github.com/java-native-access/jna

This project is licensed under the `MIT` license.

# For mod developers

## Changing code

1. Clone the repository. Then run `./gradlew genSources`
2. Edit the code you want.
3. To build run the following command:

```
./gradlew build
```
