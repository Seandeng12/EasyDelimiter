package net.seandeng.delimiter.builder;

import net.seandeng.delimiter.handler.TransmitHandler;
import net.seandeng.delimiter.handler.WriteHandler;
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
     * @param writeHandler
     * @return T
     */
    public T registerWriteHandler(WriteHandler writeHandler) {
        if (parameter().getWriteHandler() == null) {
            parameter().setWriteHandler(new WriteHandler() {
                @Override
                public void handle(Object value) {
                    super.handle(value);
                }
            });
        }
        parameter().setWriteHandler(writeHandler);
        return self();
    }

    public T registerTransmitListener(TransmitHandler transmitHandler) {
        if (parameter().getTransmitHandler() == null) {
            parameter().setTransmitHandler(new TransmitHandler() {
                @Override
                public String print() {
                    return "";
                }
            });
        }
        parameter().setTransmitHandler(transmitHandler);
        return self();
    }
}
