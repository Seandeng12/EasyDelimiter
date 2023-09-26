package net.seandeng.delimiter.read.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * work book
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class ReadWorkbook extends ReadBasicParameter {

    /**
     * Read file
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private String file;
}
