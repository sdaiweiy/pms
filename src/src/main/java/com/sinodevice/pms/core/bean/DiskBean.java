package com.sinodevice.pms.core.bean;

import java.io.Serializable;

public class DiskBean implements Serializable {
    private String name;
    private String mount;
    private String type;
    private String total;
    private String usable;
    private String percentage;

    public DiskBean() {
    }

    public String getName() {
        return this.name;
    }

    public String getMount() {
        return this.mount;
    }

    public String getType() {
        return this.type;
    }

    public String getTotal() {
        return this.total;
    }

    public String getUsable() {
        return this.usable;
    }

    public String getPercentage() {
        return this.percentage;
    }

    public DiskBean setName(String var1) {
        this.name = var1;
        return this;
    }

    public DiskBean setMount(String var1) {
        this.mount = var1;
        return this;
    }

    public DiskBean setType(String var1) {
        this.type = var1;
        return this;
    }

    public DiskBean setTotal(String var1) {
        this.total = var1;
        return this;
    }

    public DiskBean setUsable(String var1) {
        this.usable = var1;
        return this;
    }

    public DiskBean setPercentage(String var1) {
        this.percentage = var1;
        return this;
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof DiskBean)) {
            return false;
        } else {
            DiskBean var2 = (DiskBean)var1;
            if (!var2.canEqual(this)) {
                return false;
            } else {
                String var3 = this.getName();
                String var4 = var2.getName();
                if (var3 == null) {
                    if (var4 != null) {
                        return false;
                    }
                } else if (!var3.equals(var4)) {
                    return false;
                }

                String var5 = this.getMount();
                String var6 = var2.getMount();
                if (var5 == null) {
                    if (var6 != null) {
                        return false;
                    }
                } else if (!var5.equals(var6)) {
                    return false;
                }

                String var7 = this.getType();
                String var8 = var2.getType();
                if (var7 == null) {
                    if (var8 != null) {
                        return false;
                    }
                } else if (!var7.equals(var8)) {
                    return false;
                }

                label62: {
                    String var9 = this.getTotal();
                    String var10 = var2.getTotal();
                    if (var9 == null) {
                        if (var10 == null) {
                            break label62;
                        }
                    } else if (var9.equals(var10)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    String var11 = this.getUsable();
                    String var12 = var2.getUsable();
                    if (var11 == null) {
                        if (var12 == null) {
                            break label55;
                        }
                    } else if (var11.equals(var12)) {
                        break label55;
                    }

                    return false;
                }

                String var13 = this.getPercentage();
                String var14 = var2.getPercentage();
                if (var13 == null) {
                    if (var14 != null) {
                        return false;
                    }
                } else if (!var13.equals(var14)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object var1) {
        return var1 instanceof DiskBean;
    }

    public int hashCode() {
        boolean var1 = true;
        byte var2 = 1;
        String var3 = this.getName();
        int var9 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
        String var4 = this.getMount();
        var9 = var9 * 59 + (var4 == null ? 43 : var4.hashCode());
        String var5 = this.getType();
        var9 = var9 * 59 + (var5 == null ? 43 : var5.hashCode());
        String var6 = this.getTotal();
        var9 = var9 * 59 + (var6 == null ? 43 : var6.hashCode());
        String var7 = this.getUsable();
        var9 = var9 * 59 + (var7 == null ? 43 : var7.hashCode());
        String var8 = this.getPercentage();
        var9 = var9 * 59 + (var8 == null ? 43 : var8.hashCode());
        return var9;
    }

    public String toString() {
        return "Disk(name=" + this.getName() + ", mount=" + this.getMount() + ", type=" + this.getType() + ", total=" + this.getTotal() + ", usable=" + this.getUsable() + ", percentage=" + this.getPercentage() + ")";
    }
}
