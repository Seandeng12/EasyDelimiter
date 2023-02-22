package net.seandeng.delimiter.handler;

import cn.hutool.core.io.file.FileWriter;
import net.seandeng.delimiter.transmit.TransmitProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * transmit handler
 *
 * @author sean
 */
public abstract class TransmitHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransmitHandler.class);

    private static final String DEFAULT = "txt";

    private TransmitProviderService transmitService;

    public TransmitHandler() {

    }

    public TransmitHandler(TransmitProviderService transmitService) {
        LOGGER.info("init TransmitHandler with transmit service");
        this.transmitService = transmitService;
    }

    public void invoke(StringBuilder result) {
        final File file = this.before(result);
        this.transmit(file);
    }

    protected abstract String print();

    /**
     * before transmit
     */
    public File before(StringBuilder result) {
        String timestamp = System.currentTimeMillis() + "";
        FileWriter fileWriter = FileWriter.create(new File(timestamp + "." + DEFAULT));
        return fileWriter.write(result.toString());
    }

    /**
     * handle the value
     */
    private void transmit(File file) {
        if (transmitService != null) {
            transmitService.transmit(file);
        }
    }
}
