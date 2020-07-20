package com.sinodevice.pms.core.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

public class SuperEntity extends BeanConverter {
    @ApiModelProperty("主键 ID")
    @TableId(type=IdType.AUTO)
    private Long id;

    public SuperEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public SuperEntity setId(Long var1) {
        this.id = var1;
        return this;
    }

    public String toString() {
        return "SuperEntity(id=" + this.getId() + ")";
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof SuperEntity)) {
            return false;
        } else {
            SuperEntity var2 = (SuperEntity)var1;
            if (!var2.canEqual(this)) {
                return false;
            } else {
                Long var3 = this.getId();
                Long var4 = var2.getId();
                if (var3 == null) {
                    if (var4 != null) {
                        return false;
                    }
                } else if (!var3.equals(var4)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object var1) {
        return var1 instanceof SuperEntity;
    }

    public int hashCode() {
        boolean var1 = true;
        byte var2 = 1;
        Long var3 = this.getId();
        int var4 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
        return var4;
    }
}