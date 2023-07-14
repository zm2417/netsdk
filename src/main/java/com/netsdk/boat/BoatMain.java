package com.netsdk.boat;

import javax.swing.*;
import java.util.Date;
import java.util.Timer;

public class BoatMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BoatFrame demo = new BoatFrame();
                demo.setVisible(true);
                demo.startBoatFrame();
            }
        });
//        BoatFile.haveBoat();
//        BoatFile.noBoat();
//        BoatFile.haveBoat();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new BoatTimeTask(), new Date(), 60 * 1000);
    }

}
