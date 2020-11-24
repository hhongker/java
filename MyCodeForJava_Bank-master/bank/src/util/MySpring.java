package util;

import java.util.HashMap;
import java.util.HashSet;

public class MySpring {
    public static <E>E obj(String className) {
        HashMap<String, E> hashMap = new HashMap<String, E>();
        E obj = null;
        try {
            Class clazz = Class.forName(className);
            if(hashMap.size() != 0){
                obj = hashMap.get(className);
            }else {
                obj = (E)clazz.newInstance();
                hashMap.put(className, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
