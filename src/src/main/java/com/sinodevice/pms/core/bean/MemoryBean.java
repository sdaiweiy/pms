package com.sinodevice.pms.core.bean;
import java.io.Serializable;

public class MemoryBean implements Serializable {
    private String total;
    private String used;
    private String usedPercentage;
    private String available;
    private String availablePercentage;

    public MemoryBean() {
    }

    public String getTotal() {
        return this.total;
    }

    public String getUsed() {
        return this.used;
    }

    public String getUsedPercentage() {
        return this.usedPercentage;
    }

    public String getAvailable() {
        return this.available;
    }

    public String getAvailablePercentage() {
        return this.availablePercentage;
    }

    public MemoryBean setTotal(String var1) {
        this.total = var1;
        return this;
    }

    public MemoryBean setUsed(String var1) {
        this.used = var1;
        return this;
    }

    public MemoryBean setUsedPercentage(String var1) {
        this.usedPercentage = var1;
        return this;
    }

    public MemoryBean setAvailable(String var1) {
        this.available = var1;
        return this;
    }

    public MemoryBean setAvailablePercentage(String var1) {
        this.availablePercentage = var1;
        return this;
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof MemoryBean)) {
            return false;
        } else {
            MemoryBean var2 = (MemoryBean) var1;
            if (!var2.canEqual(this)) {
                return false;
            } else {
                label71: {
                    String var3 = this.getTotal();
                    String var4 = var2.getTotal();
                    if (var3 == null) {
                        if (var4 == null) {
                            break label71;
                        }
                    } else if (var3.equals(var4)) {
                        break label71;
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

                label57: {
                    String var7 = this.getUsedPercentage();
                    String var8 = var2.getUsedPercentage();
                    if (var7 == null) {
                        if (var8 == null) {
                            break label57;
                        }
                    } else if (var7.equals(var8)) {
                        break label57;
                    }

                    return false;
                }

                String var9 = this.getAvailable();
                String var10 = var2.getAvailable();
                if (var9 == null) {
                    if (var10 != null) {
                        return false;
                    }
                } else if (!var9.equals(var10)) {
                    return false;
                }

                String var11 = this.getAvailablePercentage();
                String var12 = var2.getAvailablePercentage();
                if (var11 == null) {
                    if (var12 == null) {
                        return true;
                    }
                } else if (var11.equals(var12)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object var1) {
        return var1 instanceof MemoryBean;
    }

    public int hashCode() {
        boolean var1 = true;
        byte var2 = 1;
        String var3 = this.getTotal();
        int var8 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
        String var4 = this.getUsed();
        var8 = var8 * 59 + (var4 == null ? 43 : var4.hashCode());
        String var5 = this.getUsedPercentage();
        var8 = var8 * 59 + (var5 == null ? 43 : var5.hashCode());
        String var6 = this.getAvailable();
        var8 = var8 * 59 + (var6 == null ? 43 : var6.hashCode());
        String var7 = this.getAvailablePercentage();
        var8 = var8 * 59 + (var7 == null ? 43 : var7.hashCode());
        return var8;
    }

    public String toString() {
        return "Memory(total=" + this.getTotal() + ", used=" + this.getUsed() + ", usedPercentage=" + this.getUsedPercentage() + ", available=" + this.getAvailable() + ", availablePercentage=" + this.getAvailablePercentage() + ")";
    }
}
