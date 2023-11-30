# meerkat-mango-bff
Meerkat Mango's BFF (Backend for front end)

## Run in Debug Mode
```shell
./mvnw spring-boot:run  -Dspring-boot.run.profiles=$PROFILE -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6060"
```