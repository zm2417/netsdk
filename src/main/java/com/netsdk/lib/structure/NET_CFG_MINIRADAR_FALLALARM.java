package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description 跌倒异常报警
 * @date 2022/08/04 10:13:30
 */
public class NET_CFG_MINIRADAR_FALLALARM extends NetSDKLib.SdkStructure {
    /**
     * 使能开关
     */
    public int bEnable;
    /**
     * 单位秒, 报警延迟时间
     */
    public int nDelay;
    /**
     * 关联报警输出端口，每一bit代表一路报警输出
     */
    public int nAlarmOutMask;
    /**
     * 保留字节
     */
    public byte[] szReserved = new byte[1020];

    public NET_CFG_MINIRADAR_FALLALARM() {
    }

}