package net.seandeng.delimiter.builder;

import net.seandeng.delimiter.DelimiterWriter;

/**
 * 分隔符写实现类
 *
 * @author sean.deng
 */
public class DelimiterWriterBuilder {

//    private final WriteWorkbook writeWorkbook;

    public DelimiterWriterBuilder() {
//        this.writeWorkbook = new WriteWorkbook();
    }

//    public DelimiterWriterBuilder bin(String binName) {
//        writeWorkbook.setBinName(binName);
//        return this;
//    }

    public DelimiterWriter build() {
        return new DelimiterWriter();
    }

    public DelimiterWriterLocateBuilder locate() {
        return locate();
    }
}
