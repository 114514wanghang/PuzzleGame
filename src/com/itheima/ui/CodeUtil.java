package com.itheima.ui;

import java.util.Random;

public class CodeUtil {

    public static StringBuilder getCode() {
        Random random = new Random();
        char[] arr_word = new char[62];
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            arr_word[i] = (char) ('a' + i);
            arr_word[i + 26] = (char) ('A' + i);
        }
        for (int i = 0; i < 10; i++) {
            arr_word[i + 52] = (char) ('0' + i);
        }
        for(int i = 0; i < 4; i++){
            int number = random.nextInt(arr_word.length);
            code.append(arr_word[number]);
        }
        // 随机生成一个数字
        int num = random.nextInt(10);
        // 随机选择插入位置（0-4之间）
        int insertPos = random.nextInt(code.length() + 1);
        code.insert(insertPos, num);
        return code;
    }
}
