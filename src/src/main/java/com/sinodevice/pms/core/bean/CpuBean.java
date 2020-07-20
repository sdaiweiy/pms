package com.sinodevice.pms.core.bean;

import java.io.Serializable;

public class CpuBean implements Serializable {
    private String name;
    private String power;
    private Integer kernel;
    private String used;
    private String user;
    private String system;
    private String nice;
    private String idle;

    public CpuBean() {
    }

    public String getName() {
        return this.name;
    }

    public String getPower() {
        return this.power;
    }

    public Integer getKernel() {
        return this.kernel;
    }

    public String getUsed() {
        return this.used;
    }

    public String getUser() {
        return this.user;
    }

    public String getSystem() {
        return this.system;
    }

    public String getNice() {
        return this.nice;
    }

    public String getIdle() {
        return this.idle;
    }

    public CpuBean setName(String var1) {
        this.name = var1;
        return this;
    }

    public CpuBean setPower(String var1) {
        this.power = var1;
        return this;
    }

    public CpuBean setKernel(Integer var1) {
        this.kernel = var1;
        return this;
    }

    public CpuBean setUsed(String var1) {
        this.used = var1;
        return this;
    }

    public CpuBean setUser(String var1) {
        this.user = var1;
        return this;
    }

    public CpuBean setSystem(String var1) {
        this.system = var1;
        return this;
    }

    public CpuBean setNice(String var1) {
        this.nice = var1;
        return this;
    }

    public CpuBean setIdle(String var1) {
        this.idle = var1;
        return this;
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof CpuBean)) {
            return false;
        } else {
            CpuBean var2 = (CpuBean)var1;
            if (!var2.canEqual(this)) {
                return false;
            } else {
                label107: {
                    String var3 = this.getName();
                    String var4 = var2.getName();
                    if (var3 == null) {
                        if (var4 == null) {
                            break label107;
                        }
                    } else if (var3.equals(var4)) {
                        break label107;
                    }

                    return false;
                }

                String var5 = this.getPower();
                String var6 = var2.getPower();
                if (var5 == null) {
                    if (var6 != null) {
                        return false;
                    }
                } else if (!var5.equals(var6)) {
                    return false;
                }

                Integer var7 = this.getKernel();
                Integer var8 = var2.getKernel();
                if (var7 == null) {
                    if (var8 != null) {
                        return false;
                    }
                } else if (!var7.equals(var8)) {
                    return false;
                }

                label86: {
                    String var9 = this.getUsed();
                    String var10 = var2.getUsed();
                    if (var9 == null) {
                        if (var10 == null) {
                            break label86;
                        }
                    } else if (var9.equals(var10)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    String var11 = this.getUser();
                    String var12 = var2.getUser();
                    if (var11 == null) {
                        if (var12 == null) {
                            break label79;
                        }
                    } else if (var11.equals(var12)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    String var13 = this.getSystem();
                    String var14 = var2.getSystem();
                    if (var13 == null) {
                        if (var14 == null) {
                            break label72;
                        }
                    } else if (var13.equals(var14)) {
                        break label72;
                    }

                    return false;
                }

                String var15 = this.getNice();
                String var16 = var2.getNice();
                if (var15 == null) {
                    if (var16 != null) {
                        return false;
                    }
                } else if (!var15.equals(var16)) {
                    return false;
                }

                String var17 = this.getIdle();
                String var18 = var2.getIdle();
                if (var17 == null) {
                    if (var18 != null) {
                        return false;
                    }
                } else if (!var17.equals(var18)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object var1) {
        return var1 instanceof CpuBean;
    }

    public int hashCode() {
        boolean var1 = true;
        byte var2 = 1;
        String var3 = this.getName();
        int var11 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
        String var4 = this.getPower();
        var11 = var11 * 59 + (var4 == null ? 43 : var4.hashCode());
        Integer var5 = this.getKernel();
        var11 = var11 * 59 + (var5 == null ? 43 : var5.hashCode());
        String var6 = this.getUsed();
        var11 = var11 * 59 + (var6 == null ? 43 : var6.hashCode());
        String var7 = this.getUser();
        var11 = var11 * 59 + (var7 == null ? 43 : var7.hashCode());
        String var8 = this.getSystem();
        var11 = var11 * 59 + (var8 == null ? 43 : var8.hashCode());
        String var9 = this.getNice();
        var11 = var11 * 59 + (var9 == null ? 43 : var9.hashCode());
        String var10 = this.getIdle();
        var11 = var11 * 59 + (var10 == null ? 43 : var10.hashCode());
        return var11;
    }

    public String toString() {
        return "Cpu(name=" + this.getName() + ", power=" + this.getPower() + ", kernel=" + this.getKernel() + ", used=" + this.getUsed() + ", user=" + this.getUser() + ", system=" + this.getSystem() + ", nice=" + this.getNice() + ", idle=" + this.getIdle() + ")";
    }
}
