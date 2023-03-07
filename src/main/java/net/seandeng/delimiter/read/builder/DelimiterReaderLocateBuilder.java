package net.seandeng.delimiter.read.builder;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.DelimiterReader;
import net.seandeng.delimiter.read.metadata.ReadFile;

/**
 * Delimiter Writer Locate Builder
 *
 * @author sean.dneg
 */
@Slf4j
public class DelimiterReaderLocateBuilder extends AbstractDelimiterReaderParameterBuilder<DelimiterReaderLocateBuilder, ReadFile> {

    private DelimiterReader delimiterReader;

    private ReadFile readFile;

    public DelimiterReaderLocateBuilder() {
        this.readFile = new ReadFile();
    }

    public DelimiterReaderLocateBuilder(DelimiterReader delimiterReader) {
        this.readFile = new ReadFile();
        this.delimiterReader = delimiterReader;
    }

    public DelimiterReaderLocateBuilder fileName(String fileName) {
        readFile.setFileName(fileName);
        return this;
    }

    /**
     *
     * @param fileDir
     * @return
     */
    public DelimiterReaderLocateBuilder fileDir(String fileDir) {
        readFile.setFileDir(fileDir);
        return this;
    }

    public ReadFile build() {
        return readFile;
    }

    public void doRead() {
        if (delimiterReader == null) {
            throw new RuntimeException("Must use 'EasyDelimiterFactory.read().locate()' to call this method");
        }
        delimiterReader.read(build());
        delimiterReader.finish();
    }

    @Override
    protected ReadFile parameter() {
        return readFile;
    }
}
