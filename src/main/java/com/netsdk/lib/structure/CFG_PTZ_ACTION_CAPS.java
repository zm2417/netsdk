package com.netsdk.lib.structure;/**
 * @author 47081
 * @descriptio
 * @date 2020/11/9
 * @version 1.0
 */

import com.netsdk.lib.NetSDKLib;

import java.util.Arrays;

/**
 * @author 47081
 * @version 1.0
 * @description 支持的云台动作类型
 * @date 2020/11/9
 */
public class CFG_PTZ_ACTION_CAPS extends NetSDKLib.SdkStructure {
    /**
     * 是否支持水平移动
     */
    public boolean bSupportPan;
    /**
     * 是否支持垂直移动
     */
    public boolean bSupportTile;
    /**
     * 是否支持变倍
     */
    public boolean bSupportZoom;
    /**
     * 预留
     */
    public byte[] byReserved = new byte[116];

    @Override
    public String toString() {
        return "CFG_PTZ_ACTION_CAPS{" +
                "bSupportPan=" + bSupportPan +
                ", bSupportTile=" + bSupportTile +
                ", bSupportZoom=" + bSupportZoom +
                '}';
    }
}
