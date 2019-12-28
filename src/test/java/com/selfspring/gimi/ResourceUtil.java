package com.selfspring.gimi;

/**
 * Created by ckyang on 2019/12/27.
 */
public class ResourceUtil {

    //user.dir在哪里执行命令,就代表哪个路径
    //idea中执行,都是在项目目录下,所以代表项目路径
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    //test/resource下面的文件
    public static String getTestResourcePath(String name) {
        return ResourceUtil.class.getResource("/" + name).getFile();
    }


    public static void main(String[] args) {
        ResourceUtil util = new ResourceUtil();
        System.out.println(util.getTestResourcePath("excel-to-json.xlsx"));
    }
}
