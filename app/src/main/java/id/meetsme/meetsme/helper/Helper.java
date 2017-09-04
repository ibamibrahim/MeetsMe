package id.meetsme.meetsme.helper;

import com.google.gson.Gson;

/**
 * Created by Ibam on 8/28/2017.
 */

public class Helper {

    public static <T> String objectToJson(T object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public static <T> T jsonToObject(String json, Class<T> classObject){
        Gson gson = new Gson();
        T object = gson.fromJson(json, classObject);

        return object;
    }
}
