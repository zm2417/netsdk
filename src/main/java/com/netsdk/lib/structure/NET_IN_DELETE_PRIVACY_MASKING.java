package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_DeletePrivacyMasking 输入参数
 * @date 2022/07/21 17:20:13
 */
public class NET_IN_DELETE_PRIVACY_MASKING extends NetSDKLib.SdkStructure {
	/**
	 * 结构体大小
	 */
	public int dwSize;
	/**
	 * 通道号
	 */
	public int nChannel;
	/**
	 * 遮档块编号，从0开始
	 */
	public int nIndex;

	public NET_IN_DELETE_PRIVACY_MASKING() {
		this.dwSize = this.size();
	}
}