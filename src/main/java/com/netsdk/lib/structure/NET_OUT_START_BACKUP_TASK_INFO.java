package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.SdkStructure;
/**
 * 
 * @author 119178
 * CLIENT_StartBackupTask接口输入参数
 * {@link NetSDKLib#CLIENT_StartBackupTask}
 */
public class NET_OUT_START_BACKUP_TASK_INFO extends SdkStructure{
	
	public int               dwSize;
	
	public NET_OUT_START_BACKUP_TASK_INFO(){
        this.dwSize = this.size();
    }
}
