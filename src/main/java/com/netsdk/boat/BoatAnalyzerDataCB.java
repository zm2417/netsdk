package com.netsdk.boat;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import com.sun.jna.Pointer;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * 船只事件回调
 */
public class BoatAnalyzerDataCB implements NetSDKLib.fAnalyzerDataCallBack {

    private static final Logger log = Logger.getLogger(BoatAnalyzerDataCB.class);

    @Override
    public int invoke(NetSDKLib.LLong lAnalyzerHandle, int dwAlarmType,
                      Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize,
                      Pointer dwUser, int nSequence, Pointer reserved) {
        if (lAnalyzerHandle.longValue() == 0 || pAlarmInfo == null) {
            return -1;
        }

        if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSLINEDETECTION) {
            log.info("船只入侵");
            NetSDKLib.DEV_EVENT_CROSSLINE_INFO msg = new NetSDKLib.DEV_EVENT_CROSSLINE_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);
            printObject(msg);

        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSREGIONDETECTION) {
            log.info("船只停留");
            NetSDKLib.DEV_EVENT_CROSSREGION_INFO msg = new NetSDKLib.DEV_EVENT_CROSSREGION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);
            printObject(msg);
//            log.info("msg: " + msg.toString());
            if (msg.stuIntelliCommInfo.emClassType == NetSDKLib.EM_CLASS_TYPE.EM_CLASS_BOATDETECTION) {
                // 船只检测
                if (msg.bDirection == 0 || msg.bDirection == 2) {
                    // 船只出现
                    BoatFile.haveBoat();
                } else if (msg.bDirection == 3) {
                    // 船只消失
                    BoatFile.noBoat();
                }
            }
        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_PARKINGDETECTION) {
            log.info("非法停车事件");
            NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO msg = new NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);
            printObject(msg);
            BoatFile.haveBoat();
        }

        return 0;
    }

    public static void printObject(Object object) {
        try {
            StringBuilder sb = new StringBuilder();
            Field[] fields = object.getClass().getDeclaredFields();    // 获取对象的所有属性
            for (Field item : fields) {
                String typeName = item.getGenericType().getTypeName();    // 获取对象属性的类型
                if (typeName.equals("int") || typeName.equals("byte")) {
                    String name = item.getName();    // 获取对象属性名
                    item.setAccessible(true);
                    String t = name + " = " + item.get(object) + "; ";
                    sb.append(t);
                }
            }
            log.info("print " + object.getClass().getName() + " : " + sb.toString());
        } catch (Exception e) {
            log.info("print " + object.getClass().getName()  + "  ERROR", e);
        }
    }

    public static class Test {
        int i = 1;
        byte j = 2;
        String k = "3";
    }

    public static void main(String[] args) {
        Test test = new Test();
        BoatAnalyzerDataCB.printObject(test);
    }

}
