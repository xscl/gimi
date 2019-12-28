package com.selfspring.gimi.poi;

import com.google.gson.GsonBuilder;
import javassist.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yangchangkun
 */
public class ExcelReadUtil {

    /**
     * 供外部调用的方法
     *
     * @param filePrm    待处理Excel文件
     * @param listExPrm  表头属性集合
     * @param cPrm       对应类.Class
     * @param signHeader Excel文件中是否有表头 true 是 false 否
     * @return
     */
    public static List exe(File filePrm) {
        //准备工作
        check(filePrm);

        //得到字符串数据集合
        List<String> titles = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        getStr(filePrm, titles, contents);

        //得到对象数据集合
        return getObj(titles,contents);
    }

    private static List<FieldDeclare> convertToFieldDeclare(List<String> titles) {
        List<FieldDeclare> result= new ArrayList<>();
        for (String title : titles) {
            result.add(new FieldDeclare(title));
        }
        return result;
    }


    /**
     * 准备工作
     *
     * @param filePrm   Excel文件
     * @param listExPrm 表头对应在类中的属性名称
     * @param cPrm      对应类
     * @return 是否初始化成功
     */
    private static boolean check(File filePrm) {
        try {
            //1、文件校验
            String path = filePrm.getPath();
            InputStream inTemp = null;
            try {
                inTemp = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                System.out.println("文件路径错误或在这个路径下找不到此文件");
                return false;
            }
            if (inTemp == null) {
                System.out.println("无法得到文件流");
                return false;
            }
            inTemp.close();
            return true;
        } catch (Exception e) {
            System.out.println("HPoiUtil - - - 未知的初始化错误...");
        }
        return false;
    }


    /**
     * 数据对象化
     * 我需要的东西：结果集合List<String>（非对象化） ,  表头字段集合List<Stirng>  ， 要转换的类
     *
     * @param signHeader true代表有表头，false代表无表头
     * @return 解析Excel得到数据集合 List<Class>
     * 其实是将List<String> 转换为 List<Class>
     */
    private static List getObj(List<String> titles,List<String> contents) {
        List<Object> result = new ArrayList<>();
        Class entityClass = generateClass("ExcelEntity", convertToFieldDeclare(titles));

        //字符串集合对象化
        int titleLength = titles.size();//Excel中的数据总量
        int contentSize = contents.size();//Excel中的数据总量

        //循环读取ListS, 每一个for循环封装一个对象
        for (int sLIndex = 0; sLIndex < contentSize; sLIndex += titleLength) {
            try {
                Object o = entityClass.newInstance();//准备实例
                //循环set对象的lenListEx个属性值。一个循环后则一个对象包装完成
                for (int j = 0; j < titleLength; j++) {
                    if (sLIndex + j < contentSize) {//大步长 + 小步长 确定了数据在ListS中的索引值
                        String sData = contents.get(sLIndex + j);//拿到一个数据
                        if (StringUtils.isNotBlank(sData)) {//如果数据为nul或者""那么直接跳过，最后对象中此属性为null
                            //1、通过listEx得到的属性名组装出setter()方法全名 sSetterName
                            String sSetterName = getSetterName(j, titles);
                            //2、通过方法全名sA得到对应的方法method；通过得到的参数类型转换参数数据。最后执行setter()方法
                            invokeSetterByName(entityClass, o, sData, sSetterName);
                        }//if
                    }//if
                }
                result.add(o);//装入集合之后该做什么了呢
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//for
        return result;
    }

    private static void invokeSetterByName(Class c, Object o, String sData, String sSetterName) throws IllegalAccessException, InvocationTargetException {
        Method[] methods = c.getDeclaredMethods();//拿到所有方法
        for (Method method : methods) {
            if (sSetterName.equals(method.getName())) {//必须得到同名的方法，然后获取到setter()参数类型，
                //要先判断method的参数类型
                Class<?>[] types = method.getParameterTypes();
                String nameType = types[0].getName();//类型名称 java.lang.Float
                Object data = regression(nameType, sData);//根据类型名称将String数据转换成需要的类型的数据  sD已经判断过不为空了
                method.invoke(o, data);//执行set方法
                break;//跳出for循环
                //应该加一个标识，sign = 1 如果sign == 0 那么说明没有进入for循环，那么就说明没有setXxx的方法，就说明这个表头字段错了
            }
        }//for
    }

    private static String getSetterName(int j, List<String> listEx) {
        String sSetterName = listEx.get(j);//拿到一个Excel表头字段 name pass
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sSetterName = sb.append(Tran(sSetterName)).toString();
        return sSetterName;
    }


    /**
     * 读取Excel数据到List<String>集合中
     * 我需要的东西：被读取excel文件路径path ， excel文件中的表头长度n
     *
     * @return 解析Excel得到数据集合 List<String>
     * <p>
     * 读取总是从首行开始读取，不管有无表头
     */
    private static void getStr(File file, List titles, List contents) {
        //分情况处理Excel
        String path = file.getPath();
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("文件路径错误或在这个路径下找不到此文件");
            return;
        }
        try {
            String s = StringUtils.substringAfterLast(path, ".");//文件名，分割
            if ("xls".equals(s)) {
                doXls(in, titles, contents);
            } else if ("xlsx".equals(s)) {
                doXlsx(in, titles, contents);
            } else {
                System.out.println("文件格式不支持，请选择xls或xlsx格式的Excel文件");
            }
        } catch (IOException e) {
            System.out.println("通过文件流获取Excel对象失败");
        } finally {
            try {
                in.close();//关闭文件/流
            } catch (IOException e) {
                System.out.println("关闭文件流失败");
            }
        }
    }

    private static void doXls(InputStream in, List titles, List contents) throws IOException {
        //获取Excel文件对象
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        //获取sheet表对象
        HSSFSheet sheet = workbook.getSheetAt(0);//只允许有一个sheet表
        int nRow = sheet.getLastRowNum();//若excel表中有3行，那么nRow=2 : 0,1,2
        int nCol = sheet.getRow(0).getPhysicalNumberOfCells();
        for(int i = 0; i < nCol; i++){
            HSSFCell cell = sheet.getRow(0).getCell(i);
            if(cell == null)throw new IllegalArgumentException("标题行不能有空列");
//            titles.add(cell.toString());
            titles.add("key"+i);
        }

        for (int i = 1; i <= nRow; i++) {
            HSSFRow row = sheet.getRow(i);//row:第i+1行
            if (row != null)
                for (int j = 0; j < nCol; j++) {
                    HSSFCell cell = row.getCell(j);//cell:第i+1行第j列单元格
                    contents.add(cell == null ? "" : cell.toString());
                }
        }
    }

    private static void doXlsx(InputStream in,  List titles, List contents) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int nRow = sheet.getLastRowNum();
        int nCol = sheet.getRow(0).getPhysicalNumberOfCells();
        for(int i = 0; i < nCol; i++){
            XSSFCell cell = sheet.getRow(0).getCell(i);
            if(cell == null)throw new IllegalArgumentException("标题行不能有空列");
//            titles.add(cell.toString());
            titles.add("key"+i);
        }
        for (int i = 1; i <= nRow; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < nCol; j++) {
                XSSFCell cell = row.getCell(j);
                contents.add(cell == null ? "" : cell.toString());
            }
        }
    }


    /**
     * 根据nameType是什么类型，就把数据sD转化为什么类型的数据
     *
     * @param nameType 类型名字nameType
     * @param data     数据sD
     * @return 对应类型的数据。
     * <p>
     * 常用类型有java.lang.Float java.lang.String
     */
    private static Object regression(String nameType, String data) {
        //如果是java.lang.Integer类型，但是数据data又存在小数点，那么就只取前面的部分
        if (nameType.contains("Integer")) {
            int n = data.indexOf(".");
            if (n > 0) data = data.substring(0, n);
        }
        try {
            Class<?> c = Class.forName(nameType);
            Constructor<?> constructor = c.getConstructor(String.class);//Float有一个传入String字符串的构造方法
            Object o = constructor.newInstance(data);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串首字母转大写。（反射时通过属性名得到对应setter方法处使用）
     *
     * @param s 被转换的字符串
     * @return 转换后的字符串
     */
    private static String Tran(String s) {
        char[] chars = s.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    public static Class generateClass(String javaName, List<FieldDeclare> fields) {
        try {
            //初始化容器
            ClassPool pool = ClassPool.getDefault();
            //通过cp生成一个public新类
            String className = ExcelReadUtil.class.getPackage().getName() + "." + javaName + ".java";
            CtClass ctClass = pool.makeClass(className);
            //添加字段
            for (FieldDeclare item : fields) {
                addField(ctClass, item.getName(), pool.getCtClass(item.getClair()));
            }
            return ctClass.toClass();
        }catch (Exception e){
            throw new IllegalArgumentException("根据首行生成java对象失败");
        }
    }

    @Deprecated
    public static void generateNewClassByJavassist() throws Exception {
        //初始化容器
        ClassPool pool = ClassPool.getDefault();
        //通过cp生成一个public新类Emp.java
        CtClass ctClass = pool.makeClass("com.ppdai.cbd.ddp.util.Emp.java");
        //添加字段
        addField(ctClass, "ename", pool.getCtClass("java.lang.String"));
        //添加构造函数
        addConstructor(ctClass);
        //添加自定义方法
        addCustomMethod(ctClass);
        //自定义方法验证
        customMethodVerify(ctClass);
        //class字节码输出到文件
        classConsistence(ctClass);
        System.out.println("执行结束");
    }

    private static void classConsistence(CtClass ctClass) throws Exception {
        try {
        /*把生成的class文件写入硬盘*/
            String path = "D:\\学习\\生成java\\Emp.class";
            byte[] byArr = ctClass.toBytecode();
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(byArr);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void customMethodVerify(CtClass ctClass) throws CannotCompileException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //为了验证效果，下面使用反射执行方法printInfo
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        obj.getClass().getMethod("printInfo", new Class[]{}).invoke(obj, new Object[]{});
    }

    private static void addCustomMethod(CtClass ctClass) throws CannotCompileException {
        //添加自定义方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printInfo", new CtClass[]{}, ctClass);
        //设置自定义方法的修饰符
        ctMethod.setModifiers(Modifier.PUBLIC);
        //为自定义方法设置函数体
        StringBuffer buffer2 = new StringBuffer();
        buffer2.append("{\nSystem.out.println(\"begin!\");\n")
                .append("System.out.println(ename);\n")
                .append("System.out.println(\"over!\");\n")
                .append("}");
        ctMethod.setBody(buffer2.toString());
        ctClass.addMethod(ctMethod);
    }

    private static void addConstructor(CtClass ctClass) throws CannotCompileException {
        //制造构造函数
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        //为构造函数设置函数体
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\n")
                .append("ename=\"yy\";\n")
                .append("\n}");
        ctConstructor.setBody(buffer.toString());
        //将构造函数添加到新类中
        ctClass.addConstructor(ctConstructor);
    }

    private static void addField(CtClass ctClass, String fieldName, CtClass fieldClass) throws CannotCompileException, NotFoundException {
        //制造字段，首先制造私有化的字段
        CtField enameField = new CtField(fieldClass, fieldName, ctClass);
        //设为私有化
        enameField.setModifiers(Modifier.PRIVATE);
        //添加至类中
        ctClass.addField(enameField);

        //给字段添加get、set方法
        String getName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String setName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        ctClass.addMethod(CtNewMethod.getter(getName, enameField));
        ctClass.addMethod(CtNewMethod.setter(setName, enameField));
    }
}
