package dev.foton.chat.types;

public class OptionContainer <T extends String> implements Cloneable {
    private T value;
    private final T defaultValue;

    public OptionContainer(T value){
        defaultValue = value;
        this.value = value;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        OptionContainer<T> object = (OptionContainer<T>)super.clone();
        object.setValue(value);
        return object;
    }
}
