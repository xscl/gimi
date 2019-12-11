package com.selfspring.gimi.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.selfspring.gimi.jv8.entity.Apple;

/**
 * Created by ckyang on 2019/10/14.
 */
public class JsonCoder {
    private ParserConfig config = null;

    public JsonCoder(){
        config = new ParserConfig();
        config.setAutoTypeSupport(true);
    }

    public String encode(Object obj){
        if(obj == null){
            return "";
        }
        return JSON.toJSONString(obj, SerializerFeature.WriteClassName);
    }

    public Object decode(String str){
        if(StringUtils.isBlank(str)){
            return "";
        }
        return JSON.parse(str, config);
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        JsonCoder coder = new JsonCoder();
        System.out.println(coder.encode(apple));
//        System.out.println(coder.decode(getParseText()));
        System.out.println(JSON.parseObject(getParseText(),Apple.class));
        System.out.println(JSON.parse(getParseText()));
    }

    private static String getParseText() {
        return "{\"@type\":\"com.selfspring.gimi.jv8.entity.Apple\",\"weight\":0}";
    }

}
