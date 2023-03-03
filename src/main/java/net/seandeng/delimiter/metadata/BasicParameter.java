package net.seandeng.delimiter.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.handler.TransmitHandler;
import net.seandeng.delimiter.handler.WriteValueHandler;

/**
 * Write basic parameter
 * @author sean.deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class BasicParameter {

    /**
     * You can only choose one of the head and clazz
     */
    private Class<?> clazz;

    /**
     * custom write Handler
     */
    private WriteValueHandler writeValueHandler;

    /**
     * custom transmit handler
     */
    private TransmitHandler transmitHandler;
}
