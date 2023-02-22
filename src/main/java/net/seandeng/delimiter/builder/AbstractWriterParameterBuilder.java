package net.seandeng.delimiter.builder;

import net.seandeng.delimiter.handler.TransmitHandler;
import net.seandeng.delimiter.handler.WriteValueHandler;
import net.seandeng.delimiter.metadata.AbstractParameterBuilder;
import net.seandeng.delimiter.metadata.WriteBasicParameter;

/**
 * abstract writer parameter
 *
 * @author deng
 */
public abstract class AbstractWriterParameterBuilder<T extends AbstractWriterParameterBuilder, C extends WriteBasicParameter>
        extends AbstractParameterBuilder<T, C> {

    /**
     * register write handler
     * @param writeValueHandler
     * @return T
     */
    public T registerWriteHandler(WriteValueHandler writeValueHandler) {
        if (parameter().getWriteValueHandler() == null) {
            parameter().setWriteValueHandler(new WriteValueHandler() {
                @Override
                public void handle(Object value) {
                    super.handle(value);
                }
            });
        }
        parameter().setWriteValueHandler(writeValueHandler);
        return self();
    }

    public T registerTransmitListener(TransmitHandler transmitHandler) {
        if (parameter().getTransmitHandler() == null) {
            parameter().setTransmitHandler(new TransmitHandler());
        }
        parameter().setTransmitHandler(transmitHandler);
        return self();
    }
}
