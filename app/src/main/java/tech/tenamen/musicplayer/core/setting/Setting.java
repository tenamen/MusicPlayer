package tech.tenamen.musicplayer.core.setting;

import java.util.function.Supplier;

public abstract class Setting<V> {

    private static Supplier<Boolean> ALWAYS = () -> true;
    
    protected V value;
    private final String NAME;
    private final Supplier<Boolean> VISIBLE;

    protected Setting(String name, V value, Supplier<Boolean> visibile) {
        this.NAME = name;
        this.value = value;
        this.VISIBLE = visibile;
    }

    protected Setting(String name, V value) {
        this(name, value, ALWAYS);
    }

    public final boolean isVisible() {
        return this.VISIBLE.get();
    }

    public final V getValue() {
        return this.value;
    }

    public abstract void setValue(V value);
}
