package net.seandeng.delimiter.converters;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.ReadCellData;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ReadConverterContext<T> {
    /**
     * cell data.NotNull.
     */
    private ReadCellData<T> readCellData;
    /**
     * Content property.Nullable.
     */
    private DelimiterContentProperty contentProperty;
    /**
     * context.NotNull.
     */
    private AnalysisContext analysisContext;
}
