package com.github.kuliboqin.model;

public class Column {
    private String dbField;
    private String dbType;
    private String comment;
    private boolean isKey;
    private String defaultValue;

    private String camelField;
    private String capitalizeCamelField;
    private String javaType;

    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        isKey = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCamelField() {
        return camelField;
    }

    public void setCamelField(String camelField) {
        this.camelField = camelField;
    }

    public String getCapitalizeCamelField() {
        return capitalizeCamelField;
    }

    public void setCapitalizeCamelField(String capitalizeCamelField) {
        this.capitalizeCamelField = capitalizeCamelField;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
