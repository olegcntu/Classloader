package classLoad;

import obj.Methods;
import obj.Values;

import java.lang.reflect.*;
import java.util.ArrayList;

public class Detail {


    public static ArrayList<Methods> getMethodNames(Class clazz) {
        ArrayList<Methods> method = new ArrayList<>();

        try {
            Method[] publicMethods = clazz.getDeclaredMethods();
            for (int i = 0; i < publicMethods.length; i++) {
                Methods m = new Methods();
                try {
                    String methodsName = publicMethods[i].getName();
                    String methodsName1 = publicMethods[i].toString();
                    m.setMods(methodsName1);
                    m.setName(methodsName);

                    Class typeClass = publicMethods[i].getReturnType();
                    String fieldType = typeClass.getName();
                    m.setType(fieldType);

                    method.add(m);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Throwable ignored) {

        }
        return method;
    }

    public static ArrayList<Values> getFieldNames(Class clazz) {
        ArrayList<Values> value = new ArrayList<>();

        try {
            Field[] publicFields = clazz.getDeclaredFields();
            for (int i = 0; i < publicFields.length; i++) {
                Values v = new Values();
                try {
                    String fieldName = publicFields[i].getName();
                    if (fieldName.contains("$")) {
                        continue;
                    }
                    v.setName(fieldName);
                    String fieldName2 = publicFields[i].toString();

                    Class typeClass = publicFields[i].getType();
                    int a = publicFields[i].getModifiers();

                    String fieldType = typeClass.getName();
                    v.setType(fieldType);
                    v.setMods(fieldName2);
                    value.add(v);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } catch (Throwable e) {

        }
        return value;
    }
}
