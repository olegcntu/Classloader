package classLoad;

import obj.*;
import obj.Package;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.ClassLoader;

public class SearchOfClass {

    public ArrayList<Package> Work(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        String st = path;
        ClassLoader loader = new MyClassLoader(st);
        JarFile f = new JarFile(st);
        Enumeration<JarEntry> enumiration = f.entries();

        String s;
        Class c;
        JarEntry e;

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Package> pack = new ArrayList<>();

        while (enumiration.hasMoreElements()) {
            Classes cl = new Classes();
            Package pk = new Package();
            e = enumiration.nextElement();

            if (e.getName().contains(".class")) {
                arr.add(e.getName());

                s = e.getName().replaceAll("/", ".");
                s = s.substring(0, s.length() - 6);
                try {
                    c = Class.forName(s, false, loader);
                    pk.setName(c.getTypeName());

                    if (!pack.contains(pk)) {
                        pack.add(pk);
                    } else {
                        pk = pack.get(pack.lastIndexOf(pk));
                    }

                    if (c != null) {
                        cl.setName(c);
                        cl.thisValueSet(c);

                        pk.addClass(cl);

                    }
                } catch (Exception e2) {
                    System.out.println("Error:y" + e2.getMessage());
                }

            }
            if (e.getName().contains(".") && !e.getName().contains(".class")) {

                pk.setName(e.getName().replace("/", "."));
                if (!pack.contains(pk)) {
                    pack.add(pk);
                } else {
                    pk = pack.get(pack.lastIndexOf(pk));
                }
                Date d = new Date(Long.parseLong(String.valueOf(e.getTime())));
                String strInfo = null;

                if (e.getName().contains(".properties")) {
                    InputStream in = f.getInputStream(e);
                    byte[] buffer = new byte[(int) (e.getSize())];
                    in.read(buffer, 0, buffer.length);
                    strInfo = new String(buffer, Charset.forName("windows-1251"));
                }
                MyFiles myFiles = new MyFiles(e.getName(), Long.toString(e.getSize()), d.toString(), strInfo);
                pk.addFiles(myFiles);
            }
        }


        f.close();
        return pack;

    }

}
