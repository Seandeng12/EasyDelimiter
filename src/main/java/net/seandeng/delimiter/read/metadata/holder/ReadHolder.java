package net.seandeng.delimiter.read.metadata.holder;

import net.seandeng.delimiter.metadata.Holder;
import net.seandeng.delimiter.read.listener.ReadListener;

import java.util.List;

/**
 * Get the corresponding Holder
 *
 * @author Jiaju Zhuang
 **/
public interface ReadHolder extends Holder {

    /**
     * What handler does the currently operated cell need to execute
     *
     * @return Current {@link ReadListener}
     */
    List<ReadListener<?>> readListenerList();
}
