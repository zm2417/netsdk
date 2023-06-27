package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.enumeration.NET_ENUM_INPUT_CHANNEL_MEDIA;

import static com.netsdk.lib.NetSDKLib.MAX_PREVIEW_CHANNEL_NUM;

/**
 * 获取录播主机通道输入媒体介质出参 ，对应接口{@link NetSDKLib#CLIENT_GetInputChannelMedia}
 *
 * @author ： 47040
 * @since ： Created in 2020/9/28 16:03
 */
public class NET_OUT_GET_INPUT_CHANNEL_MEDIA extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小
     */
    public int dwSize;
    /**
     * 通道数量
     */
    public int nChannelNum;
    /**
     * 输入媒体介质 {@link NET_ENUM_INPUT_CHANNEL_MEDIA}
     */
    public int[] emInputMedia = new int[MAX_PREVIEW_CHANNEL_NUM];

    public NET_OUT_GET_INPUT_CHANNEL_MEDIA() {
        dwSize = this.size();
    }
}
