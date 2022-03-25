package obj;

import classLoad.Detail;

import java.util.ArrayList;
import java.util.Arrays;

public class Classes {
    String inst;
    String name;
    String mod;
    ArrayList<Values> value;
    ArrayList<Methods> method;
    String interfaces = "";
    String annotations;
    String construct;

    public void thisValueSet(Class c) {
        method = Detail.getMethodNames(c);
        value = Detail.getFieldNames(c);

    }

    public void setName(Class clazz) {
        if (clazz.getName().contains("$")) {
            this.inst = "inner class";
            return;
        }
        this.name = clazz.getSimpleName();
        try {
            Class myClass = clazz;
            this.annotations = Arrays.toString(myClass.getAnnotations());
        } catch (Exception e) {
            System.out.println(e);
        }
        if (clazz.isInterface())
            this.inst = "Interface";
        else if (clazz.isEnum())
            this.inst = "Enum";
        else
            this.inst = "Class";

        try {
            Class[] c = clazz.getInterfaces();


            for (int i = 0; i < c.length - 1; i++) {
                interfaces = c[i].toString() + interfaces + ", ";
            }

            interfaces = interfaces + c[c.length - 1].toString() + ". ";


        } catch (Exception e) {
        }

        if (clazz.isArray())
            this.inst = "array";
        if (clazz.getName().contains("$")) {
            this.mod = "local";
        } else this.mod = "none";
        construct = Arrays.toString(clazz.getConstructors());

    }

    public ArrayList<Values> getValue() {
        return value;
    }

    public ArrayList<Methods> getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return " " + inst + ": " + name + "; \n Mod: " + mod + ";  \n Annot:" + annotations +
                ";  \n Construct=" + construct +
                ";  \n Interface=" + interfaces;
    }


}
