package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.SdkStructure;

/**
 * @author 251823
 * @version 1.0
 * @description {@link NetSDKLib#CLIENT_GetCameraInfo}的入参
 * @date 2020/11/09
 */
public class NET_IN_GET_CAMERA_INFO extends NetSDKLib.SdkStructure {

	// 结构体大小
	public int dwSize;


	// 相机编号
	public int nCameraNo;

	public NET_IN_GET_CAMERA_INFO() {
		this.dwSize = this.size();
	}

}
