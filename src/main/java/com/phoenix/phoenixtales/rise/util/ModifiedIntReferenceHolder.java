package com.phoenix.phoenixtales.rise.util;

import net.minecraft.util.IntReferenceHolder;

public class ModifiedIntReferenceHolder extends IntReferenceHolder {

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