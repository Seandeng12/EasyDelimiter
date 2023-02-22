package net.seandeng.test;

import net.seandeng.delimiter.EasyDelimiter;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 50; i++ ) {
            Person person = new Person("name_" + i, 18, "basketball_" + i);
            data.add(person);
        }

        EasyDelimiter.write()
                .registerWriteHandler(new MyHandler())
                .registerTransmitListener(new MyTransmitHandler())
                .locate()
                .doWrite(data);
    }
}
