package Lab_1;

import obj.*;
import obj.Package;

import javax.jnlp.FileSaveService;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.ClassLoader;

public class Lab1 {

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String st = "C:/Users/PC/Desktop/Test.jar";
        ClassLoader loader = new MyClassLoader(st);
        JarFile f = new JarFile(st);
        Enumeration<JarEntry> enumiration = f.entries();

        String s;
        Class c;
        File file;
        JarEntry e;

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Classes> clases = new ArrayList<>();
        ArrayList<Package> pack = new ArrayList<>();
        ArrayList<MyFiles> files = new ArrayList<>();


        while (enumiration.hasMoreElements()) {
            Classes cl = new Classes();
            Package pk = new Package();
            e = enumiration.nextElement();

            //  System.out.println(e);//+++++++++++++++++++++вывод


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
                        cl.thisValueset(c);

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
                    String fSt = null;
                    in.read(buffer, 0, buffer.length);
                    strInfo = new String(buffer, Charset.forName("windows-1251"));
                    //System.out.println(str);
                }
                MyFiles myFiles = new MyFiles(e.getName(), Long.toString(e.getSize()), d.toString(), strInfo);
                pk.addFiles(myFiles);
            }
        }

        System.out.println("---------------");

        for (Package p : pack) {
            System.out.println(p.getName());
            for (MyFiles itFl : p.getFiles()) {
                System.out.println(itFl);
            }

            for (Classes itCl : p.getClases()) {
                System.out.println(itCl.toString());
                for (Mhetods itMet : itCl.getMhetod()) {
                    System.out.println("       " + itMet.toString());
                }
                for (Values itVel : itCl.getValue()) {
                    System.out.println("       " + itVel.toString());
                }
            }
        }

        f.close();


    }

}
