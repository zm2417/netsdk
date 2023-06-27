package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  CLIENT_SetDynamicLockTempUserID 接口出参 
* @date 2022/06/23 10:26:05
*/
public class NET_OUT_SET_DYNAMIC_LOCK_TEMP_USERID_INFO extends NetSDKLib.SdkStructure {
/** 
结构体大小
*/
public			int					dwSize;
/** 
动态密码锁错误码 {@link com.netsdk.lib.enumeration.EM_DYNAMIC_LOCK_ERRORCODE}
*/
public			int					emErrorCode;

public NET_OUT_SET_DYNAMIC_LOCK_TEMP_USERID_INFO(){
		this.dwSize=this.size();
}
}