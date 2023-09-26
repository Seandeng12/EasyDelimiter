package net.seandeng.delimiter.read.metadata.holder;

import net.seandeng.delimiter.enums.HolderEnum;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author deng
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadWorkbookHolder extends AbstractReadHolder {

    /**
     * current param
     */
    private ReadWorkbook readWorkbook;
    /**
     * Read file
     * <p>
     * If 'inputStream' and 'file' all not empty, file first
     */
    private String file;

    /**
     * Parameter sheet data
     */
    private List<ReadFile> parameterFileDataList;

    private Boolean readAll;

    public ReadWorkbookHolder(ReadWorkbook readWorkbook) {
        super(readWorkbook, null);
        this.readWorkbook = readWorkbook;
        this.file = readWorkbook.getFile();
    }

    @Override
    public HolderEnum holderType() {
        return HolderEnum.WORKBOOK;
    }
}
