package net.seandeng.delimiter.write.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.metadata.WriteBasicParameter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Workbook
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class WriteWorkbook extends WriteBasicParameter {
    /**
     * Final output file
     * <p>
     * If 'outputStream' and 'file' all not empty, file first
     */
    private File file;
    /**
     * Final output stream
     * <p>
     * If 'outputStream' and 'file' all not empty, file first
     */
    private OutputStream outputStream;
    /**
     * charset.
     * Only work on the CSV file
     */
    private Charset charset;
    /**
     * Template input stream
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private InputStream templateInputStream;

    /**
     * Template file
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private File templateFile;
    /**
     * Default true.
     */
    private Boolean autoCloseStream;
    /**
     * Mandatory use 'inputStream' .Default is false
     */
    private Boolean mandatoryUseInputStream;
    /**
     * Whether the encryption
     * <p>
     * WARRING:Encryption is when the entire file is read into memory, so it is very memory intensive.
     *
     */
    private String password;
    /**
     * Write excel in memory. Default false, the cache file is created and finally written to excel.
     * <p>
     * Comment and RichTextString are only supported in memory mode.
     */
    private Boolean inMemory;
    /**
     * Excel is also written in the event of an exception being thrown.The default false.
     */
    private Boolean writeOnException;
}
