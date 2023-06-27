package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  CLIENT_SetDynamicLockOpenCode 接口出参 
* @date 2022/06/23 10:29:54
*/
public class NET_OUT_SET_DYNAMIC_LOCK_OPEN_CODE_INFO extends NetSDKLib.SdkStructure {
/** 
结构体大小
*/
public			int					dwSize;
/** 
动态密码锁错误码 {@link com.netsdk.lib.enumeration.EM_DYNAMIC_LOCK_ERRORCODE}
*/
public			int					emErrorCode;

public NET_OUT_SET_DYNAMIC_LOCK_OPEN_CODE_INFO(){
		this.dwSize=this.size();
}
}