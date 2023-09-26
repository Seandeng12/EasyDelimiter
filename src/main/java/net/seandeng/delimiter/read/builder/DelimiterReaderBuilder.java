package net.seandeng.delimiter.read.builder;

import net.seandeng.delimiter.DelimiterReader;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;

/**
 * Reader Builder
 *
 * @author deng
 */
public class DelimiterReaderBuilder extends AbstractDelimiterReaderParameterBuilder<DelimiterReaderBuilder, ReadWorkbook> {

    private final ReadWorkbook readWorkbook;

    public DelimiterReaderBuilder() {
        this.readWorkbook = new ReadWorkbook();
    }

    public DelimiterReader build() {
        return new DelimiterReader(readWorkbook);
    }

    public DelimiterReaderBuilder file(String fileName) {
        readWorkbook.setFile(fileName);
        return this;
    }

    public DelimiterReaderLocateBuilder locate() {
        return locate(null, null);
    }

    public DelimiterReaderLocateBuilder locate(String fileName, String fileDir) {
        DelimiterReaderLocateBuilder delimiterReaderLocateBuilder = new DelimiterReaderLocateBuilder(build());
        if (fileName != null) {
            delimiterReaderLocateBuilder.fileName(fileName);
        }
        if (fileDir != null) {
            delimiterReaderLocateBuilder.fileDir(fileDir);
        }
        return delimiterReaderLocateBuilder;
    }

    @Override
    protected ReadWorkbook parameter() {
        return readWorkbook;
    }
}
