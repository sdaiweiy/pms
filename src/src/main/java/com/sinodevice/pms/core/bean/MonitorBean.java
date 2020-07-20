package com.sinodevice.pms.core.bean;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonitorBean {

    public static Map<String, Object> cpuMemory() {
        //HardwareAbstractionLayer var0 = (new SystemInfo()).getHardware();

        //CentralProcessor var1 = var0.getProcessor();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        long[] var2 = processor.getSystemCpuLoadTicks();
        Util.sleep(1000L);
        long[] var3 = processor.getSystemCpuLoadTicks();
        long var4 = var3[TickType.USER.getIndex()] - var2[TickType.USER.getIndex()];
        long var6 = var3[TickType.NICE.getIndex()] - var2[TickType.NICE.getIndex()];
        long var8 = var3[TickType.SYSTEM.getIndex()] - var2[TickType.SYSTEM.getIndex()];
        long var10 = var3[TickType.IDLE.getIndex()] - var2[TickType.IDLE.getIndex()];
        long var12 = var3[TickType.IOWAIT.getIndex()] - var2[TickType.IOWAIT.getIndex()];
        long var14 = var3[TickType.IRQ.getIndex()] - var2[TickType.IRQ.getIndex()];
        long var16 = var3[TickType.SOFTIRQ.getIndex()] - var2[TickType.SOFTIRQ.getIndex()];
        long var18 = var3[TickType.STEAL.getIndex()] - var2[TickType.STEAL.getIndex()];
        long var20 = var4 + var6 + var8 + var10 + var12 + var14 + var16 + var18;
        HashMap var22 = new HashMap(3);
        CpuBean var23 = new CpuBean();
        var23.setName(processor.getName());
        if (var23.getName().contains("@")) {
            var23.setPower(var23.getName().split("@")[1]);
        }

        var23.setKernel(processor.getPhysicalProcessorCount());
        var23.setUsed(String.format("%.1f%%", 100.0D * (double)(var4 + var8) / (double)var20));
        var23.setUser(String.format("%.1f%%", 100.0D * (double)var4 / (double)var20));
        var23.setSystem(String.format("%.1f%%", 100.0D * (double)var8 / (double)var20));
        var23.setNice(String.format("%.1f%%", 100.0D * (double)var6 / (double)var20));
        var23.setIdle(String.format("%.1f%%", 100.0D * (double)var10 / (double)var20));
        var22.put("cpu", var23);
        GlobalMemory var24 = hal.getMemory();
        long var25 = var24.getAvailable();
        long var27 = var24.getTotal();
        long var29 = var27 - var25;
        MemoryBean var31 = new MemoryBean();
        var31.setTotal(FormatUtil.formatBytesDecimal(var27));
        var31.setUsed(FormatUtil.formatBytesDecimal(var29));
        var31.setUsedPercentage(String.format("%.1f%%", 100.0D * (double)var29 / (double)var27));
        var31.setAvailable(FormatUtil.formatBytesDecimal(var25));
        var31.setAvailablePercentage(String.format("%.1f%%", 100.0D * (double)var25 / (double)var27));
        var22.put("system", var31);
        MemoryMXBean var32 = ManagementFactory.getMemoryMXBean();
        MemoryBean var33 = new MemoryBean();
        MemoryUsage var34 = var32.getHeapMemoryUsage();
        long var35 = var34.getMax();
        var33.setTotal(FormatUtil.formatBytesDecimal(var35));
        var33.setUsed(FormatUtil.formatBytesDecimal(var34.getUsed()));
        var33.setUsedPercentage(String.format("%.1f%%", 100.0D * (double)var34.getUsed() / (double)var35));
        long var37 = var34.getMax() - var34.getUsed();
        var33.setAvailable(FormatUtil.formatBytesDecimal(var37));
        var33.setAvailablePercentage(String.format("%.1f%%", 100.0D * (double)var37 / (double)var35));
        var22.put("jvm", var33);
        return var22;
    }

    public static Map<String, Object> jvmHeapDisk() {
        HashMap var0 = new HashMap(3);
        MemoryMXBean var1 = ManagementFactory.getMemoryMXBean();
        MemoryUsage var2 = var1.getHeapMemoryUsage();
        JvmHeapBean var3 = new JvmHeapBean();
        var3.setInit(FormatUtil.formatBytesDecimal(var2.getInit()));
        var3.setCommitted(FormatUtil.formatBytesDecimal(var2.getCommitted()));
        var3.setMax(FormatUtil.formatBytesDecimal(var2.getMax()));
        var3.setUsed(FormatUtil.formatBytesDecimal(var2.getUsed()));
        var0.put("heap", var3);
        MemoryUsage var4 = var1.getNonHeapMemoryUsage();
        JvmHeapBean var5 = new JvmHeapBean();
        var5.setInit(FormatUtil.formatBytesDecimal(var4.getInit()));
        var5.setCommitted(FormatUtil.formatBytesDecimal(var4.getCommitted()));
        var5.setMax(FormatUtil.formatBytesDecimal(var4.getMax()));
        var5.setUsed(FormatUtil.formatBytesDecimal(var4.getUsed()));
        var0.put("nonHeap", var5);
        SystemInfo var6 = new SystemInfo();
        OSFileStore[] var7 = var6.getOperatingSystem().getFileSystem().getFileStores();
        ArrayList var8 = new ArrayList();
        OSFileStore[] var9 = var7;
        int var10 = var7.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            OSFileStore var12 = var9[var11];
            DiskBean var13 = new DiskBean();
            var13.setName(var12.getName());
            var13.setMount(var12.getMount());
            var13.setType(var12.getType());
            long var14 = var12.getUsableSpace();
            long var16 = var12.getTotalSpace();
            var13.setTotal(FormatUtil.formatBytesDecimal(var16));
            var13.setUsable(FormatUtil.formatBytesDecimal(var14));
            var13.setPercentage(String.format("%.1f%%", 100.0D * (double)(var16 - var14) / (double)var16));
            var8.add(var13);
        }

        var0.put("diskList", var8);
        return var0;
    }
}
