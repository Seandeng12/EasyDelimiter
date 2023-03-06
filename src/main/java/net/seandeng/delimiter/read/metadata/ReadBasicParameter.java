package net.seandeng.delimiter.read.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.metadata.BasicParameter;
import net.seandeng.delimiter.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Read basic parameter
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class ReadBasicParameter extends BasicParameter {

    /**
     *  Custom type listener run after default
     */
    private List<ReadListener<?>> customReadListenerList;

    public ReadBasicParameter() {
        customReadListenerList = new ArrayList<>();
    }
}
