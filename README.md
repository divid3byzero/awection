# Awection Portlets
Auction implementation based on the Portlet 2.0 specification for use in a Liferay Portal. Should be used in conjunction with [Auction Theme](https://github.com/divid3byzero/awection-theme) for best results.

**Note:** 
The theme is to be used in conjunction with Liferay 6.2 GA 3 bundled with a Apache Tomcat server. 
It will **probably** also work using the Glassfish or JBoss bundle, but this has **not** been tested and is therefore not advised.
The correct version can be downloaded from [here](http://sourceforge.net/projects/lportal/files/?source=navbar). If you've never used Liferay before, I suggest you read the according documentation [here](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide).

## Available portlets
- User registration, uses Liferay API to save users to the Liferay database and put them under the conrtol of the Liferay user admininstration
- Add articles
- Show articles
- Show joined auctions
- Trading; English, Dutch and simultaneous second price auction
- Login portlet; needs to be statically referenced from a theme in the navigation template. Please refer to the official [Liferay documentation](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide) on how this is done if you do not want to use the [Auction Theme](https://github.com/divid3byzero/awection-theme).

## Usage
- Check out master branch
- Configure database:

**Note:** This portlet application assumes the usage of a MySQL database and Hibernate as the database abstraction layer.
If you wish to use a different database, please consult the according documentation for the necessary configuration parameters/attributes. This guide is for MySQL/Hibernate only.

Modify `persistence.xml` in the directory **/src/main/webapp/WEB-INF/classes/META-INF/**. This file configures the required database
connection. The relevant properties that need to be modified to your needs are:

```
<property name='javax.persistence.jdbc.url' value='jdbc:mysql://your-database-server:3306/your-database-name'/>
<property name='javax.persistence.jdbc.user' value='your-database-user'/>
<property name='javax.persistence.jdbc.password' value='database-user-password'/>
```
- Compile and package using Maven with **mvn clean package**
- Copy the resulting WAR file in the **target/** folder to **/path/to/liferay**/deploy

If Apache Tomcat is already running the WAR will be immediately deployed by the Liferay deployment mechanism. Otherwise Liferay
will add the theme WAR to its deployment queue after starting Tomcat and deploy the application after finishing the start-up routine.
