package com.github.kuliboqin;

import com.github.kuliboqin.model.Column;
import com.github.kuliboqin.utils.StringHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableEntityUtils {


    private static TableEntityUtils instant = null;

    private TableEntityUtils() {
    }

    public static TableEntityUtils getInstance() {
        if (instant == null) {
            instant = new TableEntityUtils();
        }
        return instant;
    }

    public Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Connection conn;
        Class.forName((String) Constant.templateMap.get(Constant.DB_CLASS_NAME));
        conn = DriverManager.getConnection(
                (String) Constant.templateMap.get(Constant.DB_URL),
                (String) Constant.templateMap.get(Constant.DB_USER_NAME),
                (String) Constant.templateMap.get(Constant.DB_PASSWORD));
        return conn;
    }

    public void initTable() {
        try {
            List<Column> columnList = initColumns();
            Constant.templateMap.put("columns", columnList);
            initTableComment();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void initTableComment() throws SQLException, ClassNotFoundException{
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();
        String table = (String) Constant.templateMap.get(Constant.DB_TABLE_NAME);
        ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + table);
        System.out.println("init table name【" + table + "】");
        if (rs != null && rs.next()) {
            String createDDL = rs.getString(2);
            String comment = parse(createDDL);
            Constant.templateMap.put(Constant.TABLE_COMMENT, comment);
        }
        rs.close();
    }

    /**
     * 返回注释信息
     *
     * @param all
     * @return
     */

    public String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    /**
     * @return
     */
    private List<Column> initColumns() throws SQLException, ClassNotFoundException {
        List<Column> columnList = new ArrayList<>();
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();
        String table = (String) Constant.templateMap.get(Constant.DB_TABLE_NAME);
        ResultSet rs = stmt.executeQuery("show full columns from " + table);
        System.out.println("init table name【" + table + "】");
        while (rs.next()) {
            Column column = new Column();
            String field = rs.getString("Field");
            column.setDbField(field);
            column.setCamelField(StringHelper.toCamelCase(field));
            column.setCapitalizeCamelField(StringHelper.toCapitalizeCamelCase(field));

            String type = rs.getString("Type");
            column.setDbType(type);
            column.setJavaType(dbTypeTojava(type));
            column.setKey("PRI".equalsIgnoreCase(rs.getString("Key")) ? true : false);
            column.setComment(rs.getString("comment"));
            column.setDefaultValue(rs.getString("default"));
            columnList.add(column);
        }
//          }
        rs.close();
        return columnList;

    }

    private String dbTypeTojava(String type) {
        type = type.toLowerCase();
        if (isConstantsAny(new String[]{"varchar", "nvarchar", "char"}, type)) {
            return "String";
        } else if (isConstantsAny(new String[]{"bigint"}, type)) {
            return "Long";
        } else if (isConstantsAny(new String[]{"int", "integer"}, type)) {
            return "Integer";
        } else if (isConstantsAny(new String[]{"tinyint", "bit"}, type)) {
            return "Byte";
        } else if (isConstantsAny(new String[]{"date", "datetime", "timestamp"}, type)) {
            return "Date";
        } else if (isConstantsAny(new String[]{"double", "float", "decimal"}, type)) {
            return "java.math.BigDecimal";
        } else {
            return "Object";
        }
       /* switch (type){
            case "int":
            case "integer":
                return "Integer";
            case "bigint":
                return "Long";
            case "varchar":
            case "nvarchar":
            case "char":
                return "String";
            case "tinyint":
            case "bit":
                return "Byte";
            case "datetime":
            case "date":
            case "timestamp":
                return "Date";
            case "double":
            case "float":
            case "decimal":
                return "java.math.BigDecimal";
            default: return "Object";
        }*/
    }
//    private void setMapKeyValue(String key, Object value) {
//        Constant.templateMap.put(key, value);
//    }

    private boolean isConstantsAny(String[] strings, String search) {
        for (String string : strings) {
            if (StringHelper.containsAny(string, search)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TableEntityUtils.getInstance().initTable();
    }
}
