package org.otus.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String getMaskBoundsX(String str) {
        Pattern pattern = Pattern.compile("\\[([0-9]{1,3})\\,[0-9]{2,3}\\]\\[([0-9]{2,3})\\,[0-9]{2,3}\\]$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1) + " " + matcher.group(2);
        } else {
            return "";
        }
    }

    public static String getMaskBoundsY(String str) {
        Pattern pattern = Pattern.compile("\\[[0-9]{1,3}\\,([0-9]{2,3})\\]\\[[0-9]{2,3}\\,([0-9]{2,3})\\]$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1) + " " + matcher.group(2);
        } else {
            return "";
        }
    }
}
