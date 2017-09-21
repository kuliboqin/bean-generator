package ${pojoPackage};

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
*  ${tableComment}
* @author ${author}
* @created ${sysNow}
* @version ${version}
*/
@Entity
@Table(name = "${dbTableName}")
public class ${tableClassName} implements Serializable {
    private static final long serialVersionUID = 1L;

<#list columns as column>
    /**
    *  ${column.comment}
    */<#if column.key==true>${"\r\n@Id"}</#if>
    private ${column.javaType} ${column.camelField}<#if column.defaultValue?? >=<#if column.javaType=="String">"</#if>${column.defaultValue}<#if column.javaType=="String">"</#if></#if>;
</#list>

<#list columns as column>
    /**
    * ${column.comment}
    *
    * @param ${column.camelField}
    */
    public void set${column.capitalizeCamelField}(${column.javaType} ${column.camelField}){
        this.${column.camelField} = ${column.camelField};
    }

    /**
    * ${column.comment}
    *
    * @return
    */
    public ${column.javaType} get${column.capitalizeCamelField}(){
        return ${column.camelField};
    }

</#list>

@Override
public boolean equals(Object that) {
if (this == that) {
return true;
}
if (that == null) {
return false;
}
if (getClass() != that.getClass()) {
return false;
}
${tableClassName} other = (${tableClassName}) that;
return <#list columns as column>  (this.get${column.capitalizeCamelField}() == null ? other.get${column.capitalizeCamelField}() == null : this.get${column.capitalizeCamelField}().equals(other.get${column.capitalizeCamelField}()))<#if column_has_next> && <#else>;</#if>
</#list>
}

@Override
public int hashCode() {
final int prime = 31;
int result = 1;
<#list columns as column>
    result = prime * result + ((get${column.capitalizeCamelField}() == null) ? 0 : get${column.capitalizeCamelField}().hashCode());
</#list>
    return result;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
<#list columns as column>
sb.append(", ${column.camelField}=").append(${column.camelField});
</#list>
sb.append(", serialVersionUID=").append(serialVersionUID);
sb.append("]");
return sb.toString();
}
}