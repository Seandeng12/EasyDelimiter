package net.seandeng.delimiter.read;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.DelimiterReader;
import net.seandeng.delimiter.DelimiterWriter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Delimiter Writer Locate Builder
 *
 * @author sean.dneg
 */
@Slf4j
public class DelimiterReaderLocateBuilder {

    private final DelimiterReader delimiterReader;

    public DelimiterReaderLocateBuilder(DelimiterReader delimiterReader) {
        this.delimiterReader = delimiterReader;
    }

    public void doRead() {
        if (delimiterReader == null) {
            throw new RuntimeException("Must use 'EasyDelimiterFactory.read().locate()' to call this method");
        }
        delimiterReader.read();
        delimiterReader.finish();
    }
}
