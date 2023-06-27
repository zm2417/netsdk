package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.SdkStructure;
/**
 * @author 119178
 * @description 通道相关信息
 * @date 2021/4/21
 */
public class CFG_VSP_GAYS_CHANNEL_INFO extends SdkStructure{
	public byte[]                            szId = new byte[NetSDKLib.CFG_COMMON_STRING_64];             // 通道编号	字符串（24位）
	public int                             nAlarmLevel;                            // 报警级别[1,6]	整型
}
