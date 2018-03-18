package com.path.util;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static Object json2Obj(String jsonStr, Class<?> cla) {
        Object obj = null;
        try {
            JSONParser parser = new JSONParser(new StringReader(jsonStr));
            JSONValue jsonValue = parser.nextValue();
            if (jsonValue instanceof com.sdicons.json.model.JSONArray) {
                List<Object> list = new ArrayList<Object>();
                JSONArray jsonArray = (JSONArray) jsonValue;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONValue jsonObj = jsonArray.get(i);
                    Object javaObj = JSONMapper.toJava(jsonObj, cla);
                    list.add(javaObj);
                }
                obj = list;
            } else if (jsonValue instanceof com.sdicons.json.model.JSONObject) {
                obj = JSONMapper.toJava(jsonValue, cla);
            } else {
                obj = jsonValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
        return obj;
    }

    /**
     * 将对象封装成json对象
     * @param o
     * @param response
     */
    public static void toJSON(Object o, HttpServletResponse response) {
        try {
            String str = JSONMapper.toJSON(o).render(false);
            response.addHeader("Content-Type", "application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods","POST,GET");
            response.getWriter().write(str);
        } catch (MapperException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                response.getWriter().close();
            } catch (IOException e) {
                System.out.println("空指针");
                e.printStackTrace();
            }
        }
    }

    public static String toJSONString(Object o) {
        try {
            return JSONMapper.toJSON(o).render(true);
        } catch (MapperException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toJSON(Object o){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        toJSON(o,response);
    }

}
