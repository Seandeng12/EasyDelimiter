package net.seandeng.delimiter.read.metadata.holder;

import com.alibaba.excel.util.ListUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.seandeng.delimiter.converters.DefaultConverterLoader;
import net.seandeng.delimiter.enums.HolderEnum;
import net.seandeng.delimiter.metadata.AbstractHolder;
import net.seandeng.delimiter.read.listener.ModelBuildEventListener;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.read.metadata.ReadBasicParameter;
import net.seandeng.delimiter.read.metadata.property.DelimiterReadLineProperty;

import java.util.HashMap;
import java.util.List;


/**
 * Read Holder
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractReadHolder extends AbstractHolder implements ReadHolder {

    private DelimiterReadLineProperty delimiterReadLineProperty;

    private List<ReadListener<?>> readListenerList;

    public AbstractReadHolder(ReadBasicParameter readBasicParameter, AbstractReadHolder parentAbstractReadHolder) {
        super(readBasicParameter, parentAbstractReadHolder);

        delimiterReadLineProperty = new DelimiterReadLineProperty(getClazz());
        if (parentAbstractReadHolder == null) {
            this.readListenerList = ListUtils.newArrayList();
        } else {
            this.readListenerList = ListUtils.newArrayList(parentAbstractReadHolder.getReadListenerList());
        }
        // add default listener
        if (HolderEnum.WORKBOOK.equals(holderType())) {
            readListenerList.add(new ModelBuildEventListener());
        }
        if (readBasicParameter.getCustomReadListenerList() != null
                && !readBasicParameter.getCustomReadListenerList().isEmpty()) {
            this.readListenerList.addAll(readBasicParameter.getCustomReadListenerList());
        }

        if (parentAbstractReadHolder == null) {
            setConverterMap(DefaultConverterLoader.loadDefaultReadConverter());
        } else {
            setConverterMap(new HashMap<>(parentAbstractReadHolder.getConverterMap()));
        }
    }

    @Override
    public List<ReadListener<?>> readListenerList() {
        return getReadListenerList();
    }

    @Override
    public DelimiterReadLineProperty delimiterReadLineProperty() {
        return delimiterReadLineProperty;
    }
}
