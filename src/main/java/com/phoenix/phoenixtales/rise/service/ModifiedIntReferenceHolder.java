package com.phoenix.phoenixtales.rise.service;

import net.minecraft.util.IIntArray;
import net.minecraft.util.IntReferenceHolder;

public class ModifiedIntReferenceHolder extends IntReferenceHolder {

    public static ModifiedIntReferenceHolder create(final IIntArray data, final int idx) {
        return new ModifiedIntReferenceHolder() {
            public int get() {
                return data.get(idx);
            }

            public void set(int value) {
                data.set(idx, value);
            }
        };
    }

    protected int lastKnownValue = -1;

    @Override
    public int get() {
        return 0;
    }

    @Override
    public void set(int value) {

    }

    @Override
    public boolean isDirty() {
        int i = this.get();
        boolean flag = i != this.lastKnownValue;
        this.lastKnownValue = i;
        return flag;
    }

}