# Awection Portlets
Auction implementation based on the Portlet 2.0 specification for use in a Liferay Portlet container.

**Note:** 
The theme is to be used in conjunction with Liferay 6.2 GA 3 bundled with a Apache Tomcat server. 
It will **probably** also work using the Glassfish or JBoss bundle, but this has **not** been tested and is therefore not advised.
The correct version can be downloaded from [here](http://sourceforge.net/projects/lportal/files/?source=navbar). If you've never used Liferay before, I suggest you read the according documentation [here](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide).

## Usage

1. Check out master branch
2. Configure database:

**Note:** This portlet application assumes the usage of a MySQL database and Hibernate as the database abstraction layer.
If you wish to use a different database, please consult the according documentation for the necessary configuration parameters/attributes. This guide is for MySQL/Hibernate only.

Modify `persistence.xml` in the directory **/src/main/webapp/WEB-INF/classes/META-INF/**. This file configures the required database
connection. The relevant properties that need to be modified to your needs are:

```
<property name='javax.persistence.jdbc.url' value='jdbc:mysql://your-database-server:3306/your-database-name'/>
<property name='javax.persistence.jdbc.user' value='your-database-user'/>
<property name='javax.persistence.jdbc.password' value='database-user-password'/>
```
Compile and package using Maven with **mvn clean package**
Copy the resulting WAR file in the **target/** folder to **/path/to/liferay**/deploy

If Apache Tomcat is already running the WAR will be immediately deployed by the Liferay deployment mechanism. Otherwise Liferay
will add the theme WAR to its deployment queue after starting Tomcat and deploy the application after finishing the start-up routine.
