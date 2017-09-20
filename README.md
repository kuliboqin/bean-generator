plugin load
===

```
    <build>
        <plugins>
            <plugin>
                <groupId>com.github.kuliboqin</groupId>
                <artifactId>maven-bean-generator-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <!--<port>8099</port>-->
                    <property>${basedir}/src/main/resources/resource.properties</property>
                </configuration>
            </plugin>
        </plugins>
    </build>
 ```
 
 resource.properties
 
 ```properties
 dbUrl=jdbc:mysql://ip:3306/demo?characterEncoding=utf-8
 dbClassName=com.mysql.jdbc.Driver
 dbUserName=root
 dbPassword=root
 dbTableName=table_name
 tableClassName=
 pojoPackage=com.oriental.pojo
 author=qinjian
 version=1.0
 targetPath=
```
 