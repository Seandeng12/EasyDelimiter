package net.seandeng.delimiter.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.converters.ConverterKeyBuild.ConverterKey;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractHolder implements Holder {

    private Boolean newInitialization;

    private Class<?> clazz;

    /**
     * <p>
     * Read key:
     * <p>
     * Write key:
     */
    private Map<ConverterKey, Converter<?>> converterMap;

    public AbstractHolder(BasicParameter basicParameter, AbstractHolder prentAbstractHolder) {
        this.newInitialization = Boolean.TRUE;
        if (basicParameter.getClazz() == null && prentAbstractHolder != null) {
            this.clazz = prentAbstractHolder.getClazz();
        } else {
            this.clazz = basicParameter.getClazz();
        }
    }

    public Map<ConverterKey, Converter<?>> converterMap() {
        return getConverterMap();
    }

    public boolean isNew() {
        return getNewInitialization();
    }

}
