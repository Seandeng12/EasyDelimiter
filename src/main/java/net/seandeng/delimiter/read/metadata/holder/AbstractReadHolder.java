package net.seandeng.delimiter.read.metadata.holder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.seandeng.delimiter.read.listener.ModelBuildEventListener;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.read.metadata.ReadBasicParameter;

import java.util.ArrayList;
import java.util.List;


/**
 * Read Holder
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AbstractReadHolder implements ReadHolder {

    private List<ReadListener<?>> readListenerList;

    public AbstractReadHolder(ReadBasicParameter readBasicParameter, AbstractReadHolder parentAbstractReadHolder) {
        readListenerList = new ArrayList<>();
        readListenerList.add(new ModelBuildEventListener());
        if (readBasicParameter.getCustomReadListenerList() != null
                && !readBasicParameter.getCustomReadListenerList().isEmpty()) {
            this.readListenerList.addAll(readBasicParameter.getCustomReadListenerList());
        }
    }

    @Override
    public List<ReadListener<?>> readListenerList() {
        return getReadListenerList();
    }
}
