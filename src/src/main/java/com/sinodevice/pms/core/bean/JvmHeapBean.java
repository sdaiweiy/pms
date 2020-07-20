package com.sinodevice.pms.core.bean;

import java.io.Serializable;

public class JvmHeapBean implements Serializable {
    private String init;
    private String used;
    private String committed;
    private String max;

    public JvmHeapBean() {
    }

    public String getInit() {
        return this.init;
    }

    public String getUsed() {
        return this.used;
    }

    public String getCommitted() {
        return this.committed;
    }

    public String getMax() {
        return this.max;
    }

    public JvmHeapBean setInit(String var1) {
        this.init = var1;
        return this;
    }

    public JvmHeapBean setUsed(String var1) {
        this.used = var1;
        return this;
    }

    public JvmHeapBean setCommitted(String var1) {
        this.committed = var1;
        return this;
    }

    public JvmHeapBean setMax(String var1) {
        this.max = var1;
        return this;
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof JvmHeapBean)) {
            return false;
        } else {
            JvmHeapBean var2 = (JvmHeapBean)var1;
            if (!var2.canEqual(this)) {
                return false;
            } else {
                label59: {
                    String var3 = this.getInit();
                    String var4 = var2.getInit();
                    if (var3 == null) {
                        if (var4 == null) {
                            break label59;
                        }
                    } else if (var3.equals(var4)) {
                        break label59;
                    }

                    return false;
                }

                String var5 = this.getUsed();
                String var6 = var2.getUsed();
                if (var5 == null) {
                    if (var6 != null) {
                        return false;
                    }
                } else if (!var5.equals(var6)) {
                    return false;
                }

                String var7 = this.getCommitted();
                String var8 = var2.getCommitted();
                if (var7 == null) {
                    if (var8 != null) {
                        return false;
                    }
                } else if (!var7.equals(var8)) {
                    return false;
                }

                String var9 = this.getMax();
                String var10 = var2.getMax();
                if (var9 == null) {
                    if (var10 != null) {
                        return false;
                    }
                } else if (!var9.equals(var10)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object var1) {
        return var1 instanceof JvmHeapBean;
    }

    public int hashCode() {
        boolean var1 = true;
        byte var2 = 1;
        String var3 = this.getInit();
        int var7 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
        String var4 = this.getUsed();
        var7 = var7 * 59 + (var4 == null ? 43 : var4.hashCode());
        String var5 = this.getCommitted();
        var7 = var7 * 59 + (var5 == null ? 43 : var5.hashCode());
        String var6 = this.getMax();
        var7 = var7 * 59 + (var6 == null ? 43 : var6.hashCode());
        return var7;
    }

    public String toString() {
        return "JvmHeap(init=" + this.getInit() + ", used=" + this.getUsed() + ", committed=" + this.getCommitted() + ", max=" + this.getMax() + ")";
    }
}
