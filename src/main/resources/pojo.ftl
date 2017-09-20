package ${pojoPackage};

import java.io.Serializable;
/**
*  ${tableComment}
* @author ${author}
* @created ${sysNow}
* @version ${version}
*/
public class ${tableClassName} implements Serializable {
    private static final long serialVersionUID = 1L;

<#list columns as column>
    /**
    *  ${column.comment}
    */
    private ${column.javaType} ${column.camelField};
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
}