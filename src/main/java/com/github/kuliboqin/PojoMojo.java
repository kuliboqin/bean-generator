package com.github.kuliboqin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.io.File;

@Mojo(name = "pojo")
public class PojoMojo extends AbstractMojo {

    @Parameter(required = true)
    private File property;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
//        System.out.println("Car drive...");
        ReadPropertiesUtil.initProperty(property);
        System.out.println("init table data");
        TableEntityUtils.getInstance().initTable();
        System.out.println("ready to template output");
        String outFile = Constant.templateMap.get(Constant.TARGET_PATH)
                + File.separator + "src/main/java"
                + File.separator + Constant.templateMap.get(Constant.POJO_PACKAGE).toString().replaceAll("\\.",File.separator)
                + File.separator + Constant.templateMap.get(Constant.TABLE_CLASS_NAME) + ".java";
        TemplateUtil.template("/pojo.ftl",outFile);
//        System.out.println(ReadPropertiesUtil.constant.getClassName());
//        System.out.println(ReadPropertiesUtil.constant.getPassword());

    }

/*    public static void main(String[] args) {
        TableEntityUtils.getInstance().initTable();
        TemplateUtil.template();
    }*/
}