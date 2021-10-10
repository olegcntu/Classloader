package Lab_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test {

    public static void main(String[] args) throws IOException {
        List<String> classNames = new ArrayList<String>();
        ZipInputStream Zip = new ZipInputStream(new FileInputStream("C:/Users/PC/Desktop/Test_Load.jar"));
        for (ZipEntry entry = Zip.getNextEntry(); entry != null; entry = Zip.getNextEntry()) {

            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                // This ZipEntry represents a class. Now, what class does it represent?
                String className = entry.getName().replace('/', '.'); // including ".class"
                classNames.add(className.substring(0, className.length() - ".class".length()));
            }
        }

        for (String st1:classNames  ) {
            System.out.println(st1);
        }
    }

}
