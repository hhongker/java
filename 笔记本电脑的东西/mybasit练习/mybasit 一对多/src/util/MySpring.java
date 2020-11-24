package util;

import java.util.HashMap;

public class MySpring {

    private static HashMap<String,Object> hashMap = new HashMap<>();
    public static <T>T myObj(String className){
        T obj = (T)hashMap.get(className);
        if(obj == null){
            try {
                obj = (T)Class.forName(className).newInstance();
                hashMap.put(className,obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
