package net.seandeng.delimiter.read.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
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
     * Read InputStream
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private InputStream inputStream;
    /**
     * Read file
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private File file;

    /**
     * Default true
     */
    private Boolean autoCloseStream;
}
