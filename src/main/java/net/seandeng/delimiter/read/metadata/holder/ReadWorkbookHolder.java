package net.seandeng.delimiter.read.metadata.holder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;

import java.io.File;
import java.io.InputStream;

/**
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ReadWorkbookHolder extends AbstractReadHolder {

    /**
     * current param
     */
    private ReadWorkbook readWorkbook;
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

    public ReadWorkbookHolder(ReadWorkbook readWorkbook) {
        super(readWorkbook, null);
        this.readWorkbook = readWorkbook;
        if (readWorkbook.getInputStream() != null) {
            this.inputStream = readWorkbook.getInputStream();
        }
        this.file = readWorkbook.getFile();
        if (readWorkbook.getAutoCloseStream() == null) {
            this.autoCloseStream = Boolean.TRUE;
        } else {
            this.autoCloseStream = readWorkbook.getAutoCloseStream();
        }
    }
}
