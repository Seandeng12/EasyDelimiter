package net.seandeng.delimiter;

import net.seandeng.delimiter.read.builder.DelimiterReaderBuilder;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.write.builder.DelimiterWriterBuilder;

import java.io.InputStream;

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

    public static DelimiterReaderBuilder read(InputStream inputStream, Class<?> line, ReadListener<?> readListener) {
        DelimiterReaderBuilder delimiterReaderBuilder = new DelimiterReaderBuilder();
        delimiterReaderBuilder.file(inputStream);
        if (line != null) {
            delimiterReaderBuilder.line(line);
        }
        if (readListener != null) {
            delimiterReaderBuilder.registerReadListener(readListener);
        }
        return delimiterReaderBuilder;
    }
}
