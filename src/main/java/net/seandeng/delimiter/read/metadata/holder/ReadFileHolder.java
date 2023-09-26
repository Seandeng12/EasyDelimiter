package net.seandeng.delimiter.read.metadata.holder;

import net.seandeng.delimiter.enums.HolderEnum;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.read.metadata.ReadFile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * one file on Read holder
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ReadFileHolder extends AbstractReadHolder {

    private ReadFile readFile;

    private String fileName;
    /***
     * sheetName
     */
    private String fileDir;

    private ReadWorkbookHolder parentReadWorkbookHolder;

    private Map<Integer, ReadCellData> cellMap;

    private Integer rowIndex;

    public ReadFileHolder(ReadFile readFile, ReadWorkbookHolder readWorkbookHolder) {
        super(readFile, readWorkbookHolder);
        this.readFile = readFile;
        this.parentReadWorkbookHolder = readWorkbookHolder;
        this.fileName = readFile.getFileName();
        this.fileDir = readFile.getFileDir();
        this.cellMap = new LinkedHashMap<>();
        this.rowIndex = -1;
    }

    @Override
    public HolderEnum holderType() {
        return HolderEnum.FILE;
    }
}
