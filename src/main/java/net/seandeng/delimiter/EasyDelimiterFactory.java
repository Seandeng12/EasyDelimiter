package net.seandeng.delimiter;

import net.seandeng.delimiter.read.builder.DelimiterReaderBuilder;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.write.builder.DelimiterWriterBuilder;

/**
 * Delimiter Factory
 *
 * @author admin
 */
public class EasyDelimiterFactory {

    public static DelimiterWriterBuilder write() {
        return new DelimiterWriterBuilder();
    }

    public static DelimiterReaderBuilder read() {
        return new DelimiterReaderBuilder();
    }

    public static DelimiterReaderBuilder read(String fileName, Class<?> line, ReadListener<?> readListener) {
        DelimiterReaderBuilder delimiterReaderBuilder = new DelimiterReaderBuilder();
        delimiterReaderBuilder.file(fileName);
        if (line != null) {
            delimiterReaderBuilder.line(line);
        }
        if (readListener != null) {
            delimiterReaderBuilder.registerReadListener(readListener);
        }
        return delimiterReaderBuilder;
    }
}
