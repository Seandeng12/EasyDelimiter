package net.seandeng.delimiter.event;

import net.seandeng.delimiter.read.listener.ReadListener;

/**
 * default listener
 *
 * @author deng
 */
public abstract class AnalysisEventListener<T> implements ReadListener<T> {

    public final int DEFAULT_BATCH_COUNT = 2000;

}
