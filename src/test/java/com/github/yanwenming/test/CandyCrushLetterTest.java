package com.github.yanwenming.test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CandyCrushLetterTest {
    /**
     * 删除连续重复3次的字符
     */
    @Test
    public void testDelete() {
        int repeatTime = 3;
        String input = "aabcccbbad";
        System.out.println(String.format("Input: %s", input));
        System.out.println("Output: ");
        List<Character> output = new ArrayList<>();
        while (replaceOrDelete(input, repeatTime, 1, output)) {
            input = output.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            System.out.println(String.format("%s", input));
        }
    }

    /**
     * 使用前一个字符替换重复3次的字符
     */
    @Test
    public void testReplace() {
        int repeatTime = 3;
        String input = "abcccbad";
        System.out.println(String.format("Input: %s", input));
        System.out.println("Output: ");
        List<Character> output = new ArrayList<>();
        while (replaceOrDelete(input, repeatTime, 2, output)) {
            input = output.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            System.out.println(String.format("%s", input));
        }
    }

    /**
     * 替换或删除
     *
     * @param input         输入字符串
     * @param repeatTime    重复次数
     * @param operationType 操作类型 1：删除 2：替换
     * @param output        输出结果
     * @return
     */
    private boolean replaceOrDelete(String input, int repeatTime, int operationType, List<Character> output) {
        boolean next = false;
        output.clear();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (checkRepeatTime(chars, i, repeatTime)) {
                if (operationType == 2 && i > 0) {
                    output.add(chars[i - 1]);

                    System.out.println(String.format("%s%s%s is replaced by %s", chars[i], chars[i], chars[i], chars[i - 1]));
                }
                i += repeatTime - 1;
                next = true;
            } else {
                output.add(chars[i]);
            }
        }
        return next;
    }

    /**
     * 检查是否是连续重复字符
     *
     * @param chars
     * @param start 字符位置
     * @param time  重复次数
     * @return
     */
    private boolean checkRepeatTime(char[] chars, int start, int time) {
        int end = start + time - 1;
        if (end > chars.length) return false;
        for (int i = start; i < end; i++) {
            if (chars[i] != chars[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
