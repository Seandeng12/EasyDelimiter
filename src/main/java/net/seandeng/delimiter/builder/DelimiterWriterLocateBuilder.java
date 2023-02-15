package net.seandeng.delimiter.builder;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.DelimiteWriter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * @author 文件上传路径
 */
@Slf4j
public class DelimiterWriterLocateBuilder {

    private final DelimiteWriter delimiteWriter;

    public DelimiterWriterLocateBuilder(DelimiteWriter delimiteWriter) {
        this.delimiteWriter = delimiteWriter;
    }

    public void doWrite(Collection<?> data) {
        if (delimiteWriter == null) {
            throw new RuntimeException("必须使用 'EasySeparate.write().location()' 方式调用方法！");
        }
        if (CollectionUtils.isEmpty(data)) {
            log.info("The data list is empty, no further operation！");
            return;
        }
        delimiteWriter.write(data);
        delimiteWriter.finish();
    }
}
