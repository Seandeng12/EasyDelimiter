package net.seandeng.delimiter.read.builder;

import net.seandeng.delimiter.DelimiterReader;
import net.seandeng.delimiter.read.DelimiterReaderLocateBuilder;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;

import java.io.InputStream;

/**
 * Reader Builder
 *
 * @author deng
 */
public class DelimiterReaderBuilder extends AbstractExcelReaderParameterBuilder<DelimiterReaderBuilder, ReadWorkbook> {

    private final ReadWorkbook readWorkbook;

    public DelimiterReaderBuilder() {
        this.readWorkbook = new ReadWorkbook();
    }

    public DelimiterReader build() {
        return new DelimiterReader(readWorkbook);
    }

    public DelimiterReaderLocateBuilder locate() {
        DelimiterReader delimiterReader = build();
        return new DelimiterReaderLocateBuilder(delimiterReader);
    }

    public DelimiterReaderBuilder file(InputStream inputStream) {
        readWorkbook.setInputStream(inputStream);
        return this;
    }

    @Override
    protected ReadWorkbook parameter() {
        return readWorkbook;
    }
}
