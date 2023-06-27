package com.netsdk.boat;

import javax.swing.*;

public class BoatMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BoatFrame demo = new BoatFrame();
                demo.setVisible(true);
            }
        });
    }

}
