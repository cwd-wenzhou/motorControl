package com.imc.motorcontrol.configure;

public class SampleNum {
    public static void setNum(int num) {
        SampleNum.num = num;
    }

    public static int getNum() {
        return num;
    }

    private static int num;
}
