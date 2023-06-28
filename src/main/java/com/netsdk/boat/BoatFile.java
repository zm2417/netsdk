package com.netsdk.boat;

import java.io.File;
import java.time.LocalDateTime;

public class BoatFile {

    private static final String FILE_PATH = "D:\\";
    private static final String HAVE_BOAT = FILE_PATH + "有船只.txt";
    private static final String NO_BOAT = FILE_PATH + "没有船只.txt";

    public static LocalDateTime lastBoatWarn = null;

    public static void haveBoat() {
        lastBoatWarn = LocalDateTime.now();
        // 船只出现
        File file = new File(HAVE_BOAT);
        File noBoat = new File(NO_BOAT);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (noBoat.exists()) {
                noBoat.delete();
            }
            System.out.println("创建" + HAVE_BOAT + "成功");
        } catch (Exception e) {
            System.out.println("创建" + HAVE_BOAT + "失败");
            e.printStackTrace();
        }
    }

    public static void noBoat() {
        // 船只消失
        File file = new File(NO_BOAT);
        File haveBoat = new File(HAVE_BOAT);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (haveBoat.exists()) {
                haveBoat.delete();
            }
            System.out.println("创建" + NO_BOAT + "成功");
        } catch (Exception e) {
            System.out.println("创建" + NO_BOAT + "失败");
            e.printStackTrace();
        }
    }

}
