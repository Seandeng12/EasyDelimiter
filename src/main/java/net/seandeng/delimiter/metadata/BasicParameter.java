package net.seandeng.delimiter.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.handler.TransmitHandler;
import net.seandeng.delimiter.handler.WriteHandler;

/**
 * Write basic parameter
 * @author sean.deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class BasicParameter {

    /**
     * custom write Handler
     */
    private WriteHandler writeHandler;

    /**
     * custom transmit handler
     */
    private TransmitHandler transmitHandler;
}
