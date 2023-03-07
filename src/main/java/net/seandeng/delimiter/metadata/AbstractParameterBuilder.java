package net.seandeng.delimiter.metadata;

/**
 * delimiter Builder
 *
 * @author deng
 * @param <T>
 * @param <C>
 */
public abstract class AbstractParameterBuilder<T extends AbstractParameterBuilder, C extends BasicParameter> {

    /**
     * Line List
     * @param clazz
     * @return
     */
    public T line(Class<?> clazz) {
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
