package com.github.kuliboqin;

import com.github.kuliboqin.utils.DateHelper;
import com.github.kuliboqin.utils.StringHelper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * constant key name
 */
public class Constant implements Serializable {
    private static final long serialVersionUID = 4111610309550663077L;
    //templateMap for freeMarker
    public static Map<String, Object> templateMap = new HashMap<>();

    public static final String DB_URL = "dbUrl";
    public static final String DB_CLASS_NAME = "dbClassName";
    public static final String DB_USER_NAME = "dbUserName";
    public static final String DB_PASSWORD = "dbPassword";
    public static final String DB_TABLE_NAME = "dbTableName";
    public static final String TABLE_CLASS_NAME = "tableClassName";
    public static final String TARGET_PATH = "targetPath";
    public static final String SYS_NOW = "sysNow";
    public static final String POJO_PACKAGE = "pojoPackage";
    public static final String TABLE_COMMENT = "tableComment";

    public static void processMap() {
        if (StringHelper.isBlank((String) templateMap.get(TABLE_CLASS_NAME))) {
            templateMap.put(TABLE_CLASS_NAME, StringHelper.toCapitalizeCamelCase(templateMap.get(DB_TABLE_NAME).toString()));
        }
//        if(StringHelper.isBlank((String)templateMap.get(TARGET_PATH))){
//            String path =Constant.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//            String path = Constant.class.getResource("").getPath();
        String baseTarget = (String) templateMap.get(TARGET_PATH);
        File directory = new File(StringHelper.defaultString(baseTarget));
        try {
            baseTarget = directory.getCanonicalPath();
        } catch (IOException e) {
            System.out.println("output Path error:" + e);
        }
        System.out.println("output target Path:" + baseTarget);
        templateMap.put(TARGET_PATH, baseTarget);
//            try {
//                showURL();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        templateMap.put(SYS_NOW, DateHelper.getDateTime());
    }

    public static void showURL() throws IOException {

        // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
//        File f = new File(Constant.class.getResource("/").getPath());
//        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
        File f2 = new File(Constant.class.getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径    D:\git\daotie\daotie
        File directory = new File("src/main/java/");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        // 第三种：  file:/D:/git/daotie/daotie/target/classes/
        // 只得到null
//        URL xmlpath = Constant.class.getClassLoader().getResource("");
//        System.out.println(xmlpath);


        // 第四种： D:\git\daotie\daotie 可用
        //     /Users/qinjian/Documents/backup/github/oschina/springboot-dubbox/com-oriental-common-springdata
//        System.out.println(System.getProperty("user.dir"));
         /*
          * 结果： C:\Documents and Settings\Administrator\workspace\projectName
          * 获取当前工程路径
          */

        // 第五种：  获取所有的类路径 包括jar包的路径
//        System.out.println(System.getProperty("java.class.path"));

    }
//    private String dbUrl="jdbc:mysql://192.168.201.104:3306/dubbox?characterEncoding=utf-8";
//    private String dbClassName="com.mysql.jdbc.Driver";
//    private String dbUserName="root";
//    private   String dbPassword="root";
//    private String dbTableName="auth_user";
//
//    private String tableClassName="TUser";
//    private String targetPath="";
//
//    public String getDbUrl() {
//        return dbUrl;
//    }
//
//    public void setDbUrl(String dbUrl) {
//        this.dbUrl = dbUrl;
//    }
//
//    public String getDbClassName() {
//        return dbClassName;
//    }
//
//    public void setDbClassName(String dbClassName) {
//        this.dbClassName = dbClassName;
//    }
//
//    public String getDbUserName() {
//        return dbUserName;
//    }
//
//    public void setDbUserName(String dbUserName) {
//        this.dbUserName = dbUserName;
//    }
//
//    public String getDbPassword() {
//        return dbPassword;
//    }
//
//    public void setDbPassword(String dbPassword) {
//        this.dbPassword = dbPassword;
//    }
//
//    public String getDbTableName() {
//        return dbTableName;
//    }
//
//    public void setDbTableName(String dbTableName) {
//        this.dbTableName = dbTableName;
//    }
//
//    public String getTargetPath() {
//        return targetPath;
//    }
//
//    public void setTargetPath(String targetPath) {
//        this.targetPath = targetPath;
//    }

}
