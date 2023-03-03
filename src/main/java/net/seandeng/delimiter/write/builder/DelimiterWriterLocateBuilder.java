package net.seandeng.delimiter.write.builder;

import cn.hutool.core.text.StrBuilder;
import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.DelimiterWriter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Delimiter Writer Locate Builder
 *
 * @author sean.dneg
 */
@Slf4j
public class DelimiterWriterLocateBuilder {

    private final DelimiterWriter delimiterWriter;

    public DelimiterWriterLocateBuilder(DelimiterWriter delimiterWriter) {
        this.delimiterWriter = delimiterWriter;
    }

    public void doWrite(Collection<?> data) {
        if (delimiterWriter == null) {
            throw new RuntimeException("Must use 'EasyDelimiter.write().locate()' to call this method");
        }
        if (CollectionUtils.isEmpty(data)) {
            log.info("The data list is empty, no further operation！");
            return;
        }
        delimiterWriter.write(data);
        delimiterWriter.finish();
    }
}
