package com.netsdk.lib.structure;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;


/**
 * @author 251589
 * @version V1.0
 * @Description:
 * @date 2020/12/9 19:54
 */
@Structure.FieldOrder({"dwSize","nPhotoDataLen","pszPhotoData"})
public class NET_IN_GETFACEEIGEN_INFO extends Structure{
    public int dwSize;
    public int nPhotoDataLen;          // 人脸照片数据大小
    public Pointer pszPhotoData=new Memory(200*1024);        // 人脸照片数据, 由用户申请释放, 每张照片最大为200K
    public NET_IN_GETFACEEIGEN_INFO() {

        dwSize = this.size();
    }


}
