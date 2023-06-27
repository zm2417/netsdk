package com.netsdk.boat;

import com.netsdk.common.Res;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;

import javax.swing.*;

public class BoatDisConnect implements NetSDKLib.fDisConnect {

    private static JFrame frame;

    public BoatDisConnect(JFrame jFrame) {
        frame = jFrame;
    }

    @Override
    public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
        System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);
        // 断线提示
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setTitle(Res.string().getGate() + " : " + Res.string().getDisConnectReconnecting());
            }
        });
    }
}