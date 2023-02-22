package net.seandeng.delimiter.builder;

import net.seandeng.delimiter.DelimiterWriter;
import net.seandeng.delimiter.write.metadata.WriteWorkbook;

/**
 * 分隔符写实现类
 *
 * @author sean.deng
 */
public class DelimiterWriterBuilder extends AbstractWriterParameterBuilder<DelimiterWriterBuilder, WriteWorkbook> {

    private final WriteWorkbook writeWorkbook;

    public DelimiterWriterBuilder() {
        this.writeWorkbook = new WriteWorkbook();
    }

//    public DelimiterWriterBuilder bin(String binName) {
//        writeWorkbook.setBinName(binName);
//        return this;
//    }

    public DelimiterWriter build() {
        return new DelimiterWriter(writeWorkbook);
    }

    public DelimiterWriterLocateBuilder locate() {
        DelimiterWriter delimiterWriter = build();
        return new DelimiterWriterLocateBuilder(delimiterWriter);
    }

    @Override
    protected WriteWorkbook parameter() {
        return writeWorkbook;
    }
}
