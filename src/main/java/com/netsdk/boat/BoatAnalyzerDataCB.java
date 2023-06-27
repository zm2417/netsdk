package com.netsdk.boat;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import com.sun.jna.Pointer;
import sun.nio.ch.Net;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * 船只事件回调
 */
public class BoatAnalyzerDataCB implements NetSDKLib.fAnalyzerDataCallBack {

    private static final String FILE_PATH = "D:\\";
    private static final String HAVE_BOAT = FILE_PATH + "有船只.txt";
    private static final String NO_BOAT = FILE_PATH + "没有船只.txt";

    @Override
    public int invoke(NetSDKLib.LLong lAnalyzerHandle, int dwAlarmType,
                      Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize,
                      Pointer dwUser, int nSequence, Pointer reserved) {
        if (lAnalyzerHandle.longValue() == 0 || pAlarmInfo == null) {
            return -1;
        }

        if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSLINEDETECTION) {
            System.out.println("船只入侵");
            NetSDKLib.DEV_EVENT_CROSSLINE_INFO msg = new NetSDKLib.DEV_EVENT_CROSSLINE_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);

        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSREGIONDETECTION) {
            System.out.println("船只停留");
            NetSDKLib.DEV_EVENT_CROSSREGION_INFO msg = new NetSDKLib.DEV_EVENT_CROSSREGION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);
            if (msg.stuIntelliCommInfo.emClassType == NetSDKLib.EM_CLASS_TYPE.EM_CLASS_BOATDETECTION) {
                // 船只检测
                if (msg.bDirection == '0' || msg.bDirection == '2') {
                    // 船只出现
                    File file = new File(HAVE_BOAT);
                    File noBoat = new File(NO_BOAT);
                    try {
                        file.createNewFile();
                        noBoat.delete();
                        System.out.println("创建" + HAVE_BOAT + "成功");
                    } catch (Exception e) {
                        System.out.println("创建" + HAVE_BOAT + "失败");
                        e.printStackTrace();
                    }
                } else if (msg.bDirection == '1' || msg.bDirection == '3') {
                    // 船只消失
                    File file = new File(NO_BOAT);
                    File haveBoat = new File(HAVE_BOAT);
                    try {
                        file.createNewFile();
                        haveBoat.delete();
                        System.out.println("创建" + NO_BOAT + "成功");
                    } catch (Exception e) {
                        System.out.println("创建" + NO_BOAT + "失败");
                        e.printStackTrace();
                    }
                }
            }
        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_PARKINGDETECTION) {
            System.out.println("非法停车事件");
            NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO msg = new NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);

        }

        return 0;
    }
}
