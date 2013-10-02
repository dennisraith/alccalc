
package com.dr.ac.constants;

import com.dr.ac.model.ResultModel;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class ACConsts {

    public static final int              STATUS_OK       = 232;
    public static final int              STATUS_ERROR    = 354;

    public static final int              INPUT_NUMBER    = 332;
    public static final int              INPUT_TEXT      = 342;

    public static final String           DB_FILE_NAME    = "data.txt";

    public static String                 SIS_DESCR       = "DESCR";
    public static String                 SIS_STWRZ       = "STWRZ";
    public static String                 SIS_RSTEXT      = "RSTEXT";

    public static final int              TARGET_INPUT    = 111;
    public static final int              TARGET_SETTINGS = 222;
    public static final int              TARGET_HISTORY  = 333;

    public static final SimpleDateFormat ReadableDate    = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.GERMANY);
    public static final String           PREFS_NAME      = "acprefs";
    public static final String           PREF_STARTUP    = "firststart";

    public static ResultModel calc(String stwrz, String rstextr) {

        double stwz = 0;
        double rstex = 0;

        try {
            stwz = Double.valueOf(stwrz);
            rstex = Double.valueOf(rstextr);

            return new ResultModel(stwz, rstex);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
