package com.github.kuliboqin;

import freemarker.template.Configuration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.management.Attribute;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import java.net.URL;

public class TemplateUtil {

    public static void template(String templateName,String targetFile) {
        //创建一个Configuration实例
//        URL pathStr = TemplateUtil.class.getResource("/temp.ftl");
//        System.out.println("path路径"+ pathStr);
//        final Configuration cfg;
//
//        cfg = new Configuration();

        //设置FreeMarker的模版文件夹位置
        try {
//            String path = TemplateUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//            String tempName = "/temp.ftl";
//            String tempName = "/pojo.ftl";
            InputStream in = TemplateUtil.class.getResourceAsStream(templateName);

//            cfg.setDirectoryForTemplateLoading(dirFile.getParentFile());
//            System.out.println("path路径"+dirFile.getParentFile().getPath());
//            File[] childFiles = dirFile.listFiles();
//            for (File childFile : childFiles) {
//                System.out.println("path"+dirFile.getPath());
//                if (dirFile.getName().endsWith(".ftl")) {
            Template t = new Template("pojo", new InputStreamReader(in), null);
//            Map<String, Object> root = new HashMap<String, Object>();
//
//            root.put("packageName", "com.ricky.java");
//            root.put("className", "Person");
//            root.put("author", "Ricky Fung");
//
//            List<Attribute> attr_list = new ArrayList<Attribute>();
//            attr_list.add(new Attribute("id", "Long"));
//            attr_list.add(new Attribute("name", "String"));
//            attr_list.add(new Attribute("age", "Integer"));
//            attr_list.add(new Attribute("hobby", "List<String>"));

//            root.put("attrs", attr_list);

            //创建模版对象

//                    Template t = cfg.getTemplate(dirFile.getName());
//            Map map = new HashMap();
//            map.put("user", "lavasoft");
//            map.put("url", "http://www.baidu.com/");
//            map.put("name", "百度");

//            Map<String, Object> map = new HashMap<String, Object>();
//
//            map.put("packageName", "com.ricky.java");
//            map.put("className", "Person");
//            map.put("author", "Ricky Fung");
//
//            List<Attribute> attr_list = new ArrayList<Attribute>();
//            attr_list.add(new Attribute("id", "Long"));
//            attr_list.add(new Attribute("name", "String"));
//            attr_list.add(new Attribute("age", "Integer"));
//            attr_list.add(new Attribute("hobby", "List<String>"));

//            map.put("attrs", attr_list);

            //在模版上执行插值操作，并输出到制定的输出流中
//                    ByteArrayOutputStream os = new ByteArrayOutputStream();
//            String outFile = Constant.templateMap.get(Constant.TARGET_PATH)
//                    + File.separator + Constant.templateMap.get(Constant.TABLE_CLASS_NAME) + ".java";
            Path path = Paths.get(targetFile);
            if(!path.getParent().toFile().exists()){
                path.getParent().toFile().mkdirs();
            }
            FileOutputStream os = new FileOutputStream(targetFile);//如果没有fos2.txt文件则会自动创建这个文件
            t.process(Constant.templateMap, new OutputStreamWriter(os));
            System.out.println(os.toString());
//                }
//            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}  