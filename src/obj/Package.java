package obj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Package {
    String name;
    ArrayList<Classes> clases;
    ArrayList<MyFiles> files;





    public void addClass(Classes o) {
        this.clases.add(o);
    }

    public void addFiles(MyFiles o) { this.files.add(o);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Objects.equals(name, aPackage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        clases = new ArrayList<>();
        files = new ArrayList<>();

        String arr[] = name.split("\\.");
        this.name = arr[0];

    }

    public String getName() {
        return name;
    }


    public ArrayList<Classes> getClases() {
        return clases;
    }
    public ArrayList<MyFiles> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Package{" +"name='" + name + '\'' +'}';
    }

}
