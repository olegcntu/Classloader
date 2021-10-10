package Lab_1;


import obj.Mhetods;
import obj.Values;

import java.lang.reflect.*;
import java.util.ArrayList;

public class ClassDetail {

    private ClassDetail() {
    }

    public static String getInfo(Class clazz) {
        StringBuffer buf = new StringBuffer();
        if (clazz.isAnnotation())
            buf.append("Annotation ");
        else if (clazz.isInterface())
            buf.append("+Interface ");
        else if (clazz.isEnum())
            buf.append("Enum ");
        else
            buf.append("Class---- ");
        buf.append(clazz.getName() + " ");
        if (clazz.isArray())
            buf.append("array");
        if (clazz.isLocalClass())
            buf.append("local");
        buf.append("\n");

        return buf.toString();
    }


    public static ArrayList<Mhetods> getMetodNames(Class clazz) {

        ArrayList<Mhetods> mhetod =new ArrayList<>();

        StringBuffer buf = new StringBuffer();

        try {
            Method[] publicMethods = clazz.getDeclaredMethods();
            for (int i = 0; i < publicMethods.length; i++) {
                Mhetods m = new Mhetods();
                try {
                    String methodsName = publicMethods[i].getName();

                    String methodsName1 = publicMethods[i].toString();

                    m.setMods(methodsName1);

                    m.setName(methodsName);
                    buf.append("Name: " + methodsName);


                    Class typeClass = publicMethods[i].getReturnType();
                    String fieldType = typeClass.getName();
                    m.setType(fieldType);


                    buf.append(", Type: " + fieldType + "\n");
                    mhetod.add(m);
                } catch (Exception e) {
                    buf.append("\n");
                }
               // System.out.println(m.toString());
            }
        } catch (Throwable e) {

        }
        return mhetod;
    }


    public static ArrayList<Values> getFieldNames(Class clazz) {
        StringBuffer buf = new StringBuffer();
        ArrayList<Values> value =new ArrayList<>();

        try {
            Field[] publicFields = clazz.getDeclaredFields();
            for (int i = 0; i < publicFields.length; i++) {
                Values v = new Values();
                try {
                 //   String fieldName = publicFields[i].getName();
                      String fieldName = publicFields[i].getName();
                      if(fieldName.contains("$")){continue;}
                    v.setName(fieldName);
                    String fieldName2 = publicFields[i].toString();

                   // buf.append("Name: " + fieldName);
                    Class typeClass = publicFields[i].getType();
                    int a = publicFields[i].getModifiers();

                    String fieldType = typeClass.getName();
                    String fieldType1 = typeClass.getTypeName();
                    v.setType(fieldType);
                    v.setMods(fieldName2);
                    value.add(v);
                    //buf.append(", Type: " + fieldType+" //// "+fieldName2+" //// "+a+"////"+"\n");
                } catch (Exception e) {
                    buf.append("\n");

                }
               // System.out.println(v);
            }
        } catch (Throwable e) {

        }
        return value;
    }
}
