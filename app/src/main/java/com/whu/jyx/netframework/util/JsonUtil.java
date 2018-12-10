package com.whu.jyx.netframework.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Json 工具类
 *
 * @author Wang.J
 * @date 2016-10-17
 */
public class JsonUtil {
    private JsonUtil(){}

    private static final Gson mGson;

    static{
        mGson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return mGson.fromJson(json, cls);
    }

    public static <T> T fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }

    public static String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    /**
     * 将对象里的属性转换成Map
     * @param obj 待转换的属性
     * @return 属性Map
     * @author Wang.J
     * @www ss
     */
    public static Map<String,String> convertObject2Map(Object obj){
        String json = mGson.toJson(obj);
        return  mGson.fromJson(
                json,new TypeToken<Map<String,String>>(){}.getType());
    }

    /**
     * 将Json字符串数组转换为指定类型的List结果集
     * @param json 待转换的jsonarray字符串
     * @param cls 转化的对象类型
     * @return 处理后的LIST集合
     * @author Liang.WW
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> cls){
        List<T> dataList = new ArrayList<T>();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        for (JsonElement element:jsonArray){
            T item = mGson.fromJson(element,cls);
            dataList.add(item);
        }
        return dataList;
    }
}
