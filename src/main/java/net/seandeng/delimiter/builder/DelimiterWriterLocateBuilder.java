package net.seandeng.delimiter.builder;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.DelimiterWriter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * @author 文件上传路径
 */
@Slf4j
public class DelimiterWriterLocateBuilder {

    private final DelimiterWriter delimiterWriter;

    public DelimiterWriterLocateBuilder(DelimiterWriter delimiterWriter) {
        this.delimiterWriter = delimiterWriter;
    }

    public void doWrite(Collection<?> data) {
        if (delimiterWriter == null) {
            throw new RuntimeException("必须使用 'EasySeparate.write().location()' 方式调用方法！");
        }
        if (CollectionUtils.isEmpty(data)) {
            log.info("The data list is empty, no further operation！");
            return;
        }
        delimiterWriter.write(data);
        delimiterWriter.finish();
    }
}
