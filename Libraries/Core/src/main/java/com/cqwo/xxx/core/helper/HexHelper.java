package com.cqwo.xxx.core.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 进制转换
 */
public class HexHelper {


    private static final String TAG = "HexUtils";

    static Logger logger = LoggerFactory.getLogger(HexHelper.class);

    /**
     * 十六进制转二进制
     *
     * @param s 原串
     * @return
     */
    public static String hexadecimalToBinary(String s) {

        Integer length = s.length() * 4;
        return hexadecimalToBinary(s, length);

    }

    /**
     * 十六进制转二进制
     *
     * @param s      原串
     * @param length 长度
     * @return
     */
    public static String hexadecimalToBinary(String s, Integer length) {

        try {

            Integer i = hexadecimalToDecimalism(s);

            return toBinary(i, length);

        } catch (Exception ex) {

        }
        return "";
    }

    /**
     * 二进度转十六进制
     *
     * @return
     */
    public static String binaryToHexadecimal(String s) {

        try {

            /**
             * 行转十进制
             */
            Integer i = binaryToDecimalism(s);

            return Integer.toHexString(i);

        } catch (Exception ex) {

        }
        return "";


    }

    //region 其它转十进度

    //region 二进度转十进制


    /**
     * 十进制转二进制
     *
     * @param s      字符串
     * @param length 长度
     * @return
     */
    public static String toBinary(int s, Integer length) {

        try {
            //十进制 -> 二进制
            String str = Integer.toBinaryString(s);

            while (str.length() < length) {
                str = 0 + str;
            }

            return str;

        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 二进度转十进制
     *
     * @param s
     * @return
     */
    public static Integer binaryToDecimalism(String s) {

        return binaryToDecimalism(s, 0);
    }

    /**
     * 二进度转十进制
     *
     * @param s
     * @param defaultValue
     * @return
     */
    public static Integer binaryToDecimalism(String s, Integer defaultValue) {

        try {
            s = binaryFillZore(s);
            return Integer.parseInt(s, 2);

        } catch (Exception ex) {

        }

        return defaultValue;
    }


    /**
     * 二进度填充位数
     *
     * @param s
     * @return
     */
    public static String binaryFillZore(String s) {

        Integer n = 4 - s.length() % 4;

        //System.out.println(n);

        if (n >= 1) {

            StringBuilder sBuilder = new StringBuilder(s);
            for (int i = 0; i < n; i++) {
                sBuilder.insert(0, "0");
            }
            s = sBuilder.toString();

        }

        return s;
    }

    //endregion

    //region 十六进制转十进

    /**
     * 十六进制转十进度
     *
     * @return
     */
    public static Integer hexadecimalToDecimalism(String s) {
        return hexadecimalToDecimalism(s, 0);
    }

    /**
     * 十六进制转十进度
     *
     * @return
     */
    public static Integer hexadecimalToDecimalism(String s, Integer defaultValue) {

        try {
            return Integer.parseInt(s, 16);

        } catch (Exception ex) {

        }

        return defaultValue;
    }

    /**
     * 十进制转二进制
     *
     * @param s 字符串
     * @return
     */
    public static String toHexString(int s) {

        return toHexString(s, 2);
    }

    /**
     * 十进制转十六进制
     *
     * @param s      字符串
     * @param length 长度
     * @return
     */
    public static String toHexString(int s, Integer length) {

        try {
            //十进制 -> 十六进制
            String str = Integer.toHexString(s);

            while (str.length() < length) {
                str = 0 + str;
            }

            return str;

        } catch (Exception ex) {
            return "";
        }
    }

    //endregion

    /**
     * 获取校验和cs
     *
     * @param s
     * @return
     */
    public static String hexAddtion(String s) {

        String hexCS = "";

        try {
            Integer tenCS = 0;

            if (s.length() % 2 != 0) {
                s = "0" + s;
            }

            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0) {
                    String everyHex = StringHelper.substring(s, i, 2);
                    Integer everyTen = hexadecimalToDecimalism(everyHex);
                    tenCS += everyTen;
                }
            }

            String binaryCS = toBinary(tenCS, 3);

            hexCS = binaryToHexadecimal(binaryCS);

            if (hexCS.length() > 2) {
                hexCS = StringHelper.substring(hexCS, hexCS.length() - 2, 2);
            }

            if (hexCS.length() < 2) {
                hexCS = "0" + hexCS;
            }

        } catch (Exception e) {

        }

        s += hexCS;

        return s.toUpperCase();
    }

    //endregion

    /**
     * 二进进位移运算
     *
     * @param i1
     * @param i2
     */
    public static Integer shiftoperation(int i1, Integer i2) {

        try {

            return i1 & i2;

        } catch (Exception ex) {

            logger.error(TAG, "位移动算: ", ex);
        }

        return -1;

    }

    /**
     * 二进进位移运算
     *
     * @param s1
     * @param s2
     */
    public static Integer binaryShiftoperation(String s1, String s2) {

        Integer i1 = binaryToDecimalism(s1);
        Integer i2 = binaryToDecimalism(s2);

        return shiftoperation(i1, i2);

    }


    /**
     * 十六进进位移运算
     *
     * @param s1
     * @param s2
     */
    public static Integer hexadecimalShiftoperation(String s1, String s2) {

        Integer i1 = hexadecimalToDecimalism(s1);
        Integer i2 = hexadecimalToDecimalism(s2);

        return shiftoperation(i1, i2);

    }

    /**
     * 十六进制转ASC码
     *
     * @param s
     * @return
     */
    public static String hexadecimalShiftToASCII(String s) {

        try {

            Integer i = hexadecimalToDecimalism(s);

        } catch (Exception ex) {

        }

        return "";

    }


    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }


    /**
     * 二进制度
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String binaryExclusiveOr(String s1, String s2) {

        //二进度转进制

        Integer i1 = binaryToDecimalism(s1);
        Integer i2 = binaryToDecimalism(s2);

        Integer i3 = exclusiveOr(i1, i2);

        return toBinary(i3, 8);
    }

    /**
     * 十六进的异或
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String hexExclusiveOr(String s1, String s2) {


        //二进度转进制

        Integer i1 = hexadecimalToDecimalism(s1);
        Integer i2 = hexadecimalToDecimalism(s2);

        Integer i3 = exclusiveOr(i1, i2);

        return toHexString(i3);
    }


    /**
     * 十进制异或
     *
     * @param s1
     * @param s2
     * @return
     */
    public static Integer exclusiveOr(Integer s1, Integer s2) {

        return s1 ^ s2;

    }


    /**
     * 十六进制度取反补位
     *
     * @param hexStr
     * @return
     */
    public static String hexReverse(String hexStr, Integer length) {

        Integer i = hexadecimalToDecimalism(hexStr);

        Integer value = reverse(i);

        String s = toHexString(value, length);

        return s.substring(s.length() - length);

    }

    /**
     * 二进制取反补位
     *
     * @param hexStr
     * @param length
     * @return
     */
    public static String binaryReverse(String hexStr, Integer length) {

        Integer i = binaryToDecimalism(hexStr); //System.out.println(i);

        Integer value = reverse(i);

        String s = toBinary(value, length);

        return s.substring(s.length() - length);

    }

    /**
     * 十进制取反
     *
     * @param i
     * @return
     */
    public static Integer reverse(int i) {

        return ~i;
    }

    //region 位移

    //endregion

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    //endregion

    /**
     * @param args 把char字符型数字转成int数字，因为他们的ascii码值恰好相差48，
     *             因此把char型数字减去48得到int型数据，例如'4'转换成了4
     *             '0'的ascii码是48
     */
    public static void main2(String[] args) { //System.out.println("ASCII : " + HexHelper.convertHexToString("4D4A323636"));
    }


    public static void main(String[] args) {

        String s = "8411F1301007ff";

        System.out.println(  hexAddtion(s));


        //"0000000011000010"


// //System.out.println(hexadecimalToBinary("60"));

//
//        String s1 = "60";
//
//        String s2 = "80";
//
//        String s3 = "40";
//
//        Integer i1 = hexadecimalToDecimalism(s1);
//        Integer i2 = hexadecimalToDecimalism(s2);
//        Integer i3 = hexadecimalToDecimalism(s3);
//
// //System.out.println(i1 & i2);
//
//        Integer i4 = i1 & i3;
//
//
//
// //System.out.println(toBinary(i4, 8));
//
//        i4 = i1 | i3;
// //System.out.println(toBinary(i4, 8));
//
// //System.out.println(9 & 8);
//
// //System.out.println(toBinary(8, 8));
// //System.out.println(toBinary(9, 8));
//
//
// //System.out.println(9 | 8);
// //System.out.println(8 ^ 9);
    }


}
