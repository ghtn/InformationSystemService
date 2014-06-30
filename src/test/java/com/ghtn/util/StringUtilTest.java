package com.ghtn.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihe on 14-6-30.
 */
public class StringUtilTest {

    @Test
    public void testString() {
        /*String s = "123";
        String [] ss = s.split("#");
        System.out.println(ss.length);*/

        /*Random random = new Random();
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));*/

        // 0~10, 9个不相等的随机数
        Random random = new Random();
        List<Integer> randomList = new ArrayList<>();
        while (true) {
            if (randomList.size() == 9) {
                break;
            }
            int num = random.nextInt(10);
            if (!randomList.contains(num)) {
                randomList.add(num);
            }
        }

        for (int i = 0; i < randomList.size(); i++) {
            System.out.println(randomList.get(i));
        }
    }

}
