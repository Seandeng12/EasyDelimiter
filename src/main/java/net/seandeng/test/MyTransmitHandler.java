package net.seandeng.test;

import net.seandeng.delimiter.handler.TransmitHandler;

import java.io.File;

public class MyTransmitHandler extends TransmitHandler {

    @Override
    protected String print() {
        return null;
    }

    @Override
    public File before(StringBuilder result) {
        return super.before(result);
    }
}
