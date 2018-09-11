package com.jingna.hulu.huluapp.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by a on 2018/9/11.
 */

public class Map2Json {

    public static String map2json(Map<String, String> map){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

}
