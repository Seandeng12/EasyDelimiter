package test;

import net.seandeng.delimiter.EasyDelimiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/deng/Desktop/test.txt");
        EasyDelimiter.read(new FileInputStream(file), Person.class, new CustomEventListener<Person>())
                .locate().doRead();
    }
}
