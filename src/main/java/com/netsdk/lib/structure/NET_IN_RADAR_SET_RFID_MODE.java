package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author ： 260611
 * @description ： CLIENT_SetRadarRFIDMode接口入参
 * @since ： Created in 2022/02/11 10:22
 */

public class NET_IN_RADAR_SET_RFID_MODE extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小
     */
    public int dwSize;
    /**
     * RFID规则模式，0：设备单独工作时使用，全部为有效卡；1：平台接入时使用，区分RFID卡片有效性
     */
    public int nMode;

    public NET_IN_RADAR_SET_RFID_MODE() {
        this.dwSize = this.size();
    }
}