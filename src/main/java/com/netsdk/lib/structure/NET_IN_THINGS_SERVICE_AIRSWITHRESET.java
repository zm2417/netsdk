package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description 空开恢复出厂设置输入参数(对应 EM_THINGS_SERVICE_TYPE_AIRSWITHRESET)
 * @date 2022/04/20 10:50:20
 */
public class NET_IN_THINGS_SERVICE_AIRSWITHRESET extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小, 调用者必须初始化该字段
     */
    public int dwSize;
    /**
     * 产品ID
     */
    public byte[] szProductID = new byte[64];
    /**
     * 设备ID
     */
    public byte[] szDeviceID = new byte[64];

    public NET_IN_THINGS_SERVICE_AIRSWITHRESET() {
        this.dwSize = this.size();
    }
}