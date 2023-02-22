package net.seandeng.delimiter.transmit;

import java.io.File;

/**
 * provider service
 *
 * @author sean.deng
 */
public interface TransmitProviderService {

    /**
     * transmit
     *
     * @param file
     */
     void transmit(File file);
}
