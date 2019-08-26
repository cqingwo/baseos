/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * 封装了XML转换成object，object转换成XML的代码 
 *
 * @author Steven
 *
 */
public class XMLHelper {
    /**
     * 将对象直接转换成String类型的 XML输出 
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流  
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static void convertToXml(Object obj, File file) throws IOException {

        //如果目标文件所在的文件夹不存在
        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
            // 如果目标文件所在的文件夹不存在，则创建父文件夹 //System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功 //System.out.println("创建目标文件所在的目录失败！");
            }
        }

        //创建输出文件
        if(!file.exists())
        {
            file.createNewFile();
        } //System.out.println(obj);
        //创建文件输出流
        FileOutputStream fos = new FileOutputStream(file);


        //关闭输出流
        fos.close();
    }

    /**
     * 将对象根据路径转换成xml文件 
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // 将对象转换成输出流形式的xml  
            // 创建输出流
            OutputStreamWriter out = null;
            try { //System.out.println(path);
                out = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象 
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象 
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStreamReader inputStream = null;

            try {
                inputStream = new InputStreamReader(new FileInputStream(xmlPath),"UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

}  