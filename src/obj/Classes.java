package obj;

import classLoad.Detail;

import java.util.ArrayList;
import java.util.Arrays;

public class Classes {
    String inst;
    String name;
    String mod;
    ArrayList<Values> value;
    ArrayList<Mhetods> mhetod;
    String interfaces;
    String annotations;
    String construct;

    public void thisValueset(Class c) {
        mhetod = Detail.getMetodNames(c);
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

            for (Class itCl : c) {
                interfaces = interfaces + ", " + itCl.toString();
            }
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

    public ArrayList<Mhetods> getMhetod() {
        return mhetod;
    }

    @Override
    public String toString() {
        return " " + inst + ": " + name + "; \n Mod: " + mod + ";  \n Annot:" + annotations +
                ";  \n Construct=" + construct +
                ";  \n Interface=" + interfaces;
    }


}
