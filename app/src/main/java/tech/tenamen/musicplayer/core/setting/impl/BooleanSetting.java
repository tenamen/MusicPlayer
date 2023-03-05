package tech.tenamen.musicplayer.core.setting.impl;

import com.google.common.base.Supplier;

import tech.tenamen.musicplayer.core.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {
    
    public BooleanSetting(String name, boolean value, Supplier<Boolean> visible) {
        super(name, value, visible);
    }

    public BooleanSetting(String name, boolean value) {
        super(name, value);
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
