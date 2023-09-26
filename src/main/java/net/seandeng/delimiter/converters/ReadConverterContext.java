package net.seandeng.delimiter.converters;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ReadConverterContext<T> {
    /**
     * cell data.NotNull.
     */
    private ReadCellData readCellData;
    /**
     * context.NotNull.
     */
    private AnalysisContext analysisContext;
}
