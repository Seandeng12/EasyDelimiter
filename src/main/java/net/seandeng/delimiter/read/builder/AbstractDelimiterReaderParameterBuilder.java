package net.seandeng.delimiter.read.builder;

import net.seandeng.delimiter.metadata.AbstractParameterBuilder;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.read.metadata.ReadBasicParameter;

import java.util.ArrayList;

/**
 * Build DelimiterBuilder
 *
 * @author deng
 */
public abstract class AbstractDelimiterReaderParameterBuilder<T extends AbstractDelimiterReaderParameterBuilder,
        C extends ReadBasicParameter> extends AbstractParameterBuilder<T, C> {

    public T registerReadListener(ReadListener<?> readListener) {
        if (parameter().getCustomReadListenerList() == null) {
            parameter().setCustomReadListenerList(new ArrayList<>());
        }
        parameter().getCustomReadListenerList().add(readListener);
        return self();
    }
}
