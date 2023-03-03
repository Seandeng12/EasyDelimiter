package net.seandeng.delimiter.metadata;

public abstract class AbstractParameterBuilder<T extends AbstractParameterBuilder, C extends BasicParameter> {

    public T column(Class<?> clazz) {
        parameter().setClazz(clazz);
        return self();
    }

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
