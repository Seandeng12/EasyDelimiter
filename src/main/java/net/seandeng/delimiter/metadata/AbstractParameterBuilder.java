package net.seandeng.delimiter.metadata;

public abstract class AbstractParameterBuilder<T extends AbstractParameterBuilder, C extends BasicParameter> {

    protected T self() {
        return (T)this;
    }

    /**
     * Get parameter
     *
     * @return
     */
    protected abstract C parameter();
}
