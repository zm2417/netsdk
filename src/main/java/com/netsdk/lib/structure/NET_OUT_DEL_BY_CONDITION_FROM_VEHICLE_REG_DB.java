package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description CLIENT_DeleteByConditionFromVehicleRegisterDB 接口输出参数
* @date 2022/02/25 15:08:25
*/
public class NET_OUT_DEL_BY_CONDITION_FROM_VEHICLE_REG_DB extends NetSDKLib.SdkStructure {
/** 
结构体大小
*/
public			int					dwSize;

public NET_OUT_DEL_BY_CONDITION_FROM_VEHICLE_REG_DB(){
    this.dwSize=this.size();
}
}