# wildfly-hibernate5-error
Simple Web Application using native Hibernate 5 and JTA on WildFly 10.

To run this on WildFly 10.0.0.Beta2, just do `mvn clean package` and copy `ear/target/wildfly-hibernate5-error.war` to `$WILDFLY_HOME/standalone/deployment directory`.

To run this on WildFly 9.0.1.Final you can do the same as for WildFly 10, however, following modificaitons must be done first:

* replace `hibernate5` with `hibernate4` in `./src/main/webapp/WEB-INF/flowers-service.xml`
* replace `10.0.0.Beta2` with `9.0.1.Final` in `./pom.xml`
