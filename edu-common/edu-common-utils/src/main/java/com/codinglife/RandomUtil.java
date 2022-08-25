package com.codinglife;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Description: 随机数生成工具类
 * @author: CodingLifeVV
 * @date: 2022.04.24
 */
public class RandomUtil {

    private static final Random random = new Random();

    /**
     * 4 位随机数
     */
    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

}
