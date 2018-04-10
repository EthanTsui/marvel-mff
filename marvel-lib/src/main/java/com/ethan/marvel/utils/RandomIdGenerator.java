/**
 * 
 */
package com.ethan.marvel.utils;

import java.util.Random;

/**
 * 產生的 random id
 * 
 * @author Ethan Tsui created date: 2014/1/8
 */
public class RandomIdGenerator {
    private static RandomIdGenerator instance = null;
    private static final Integer LOCK = new Integer(1);

    private Random random = new Random();
    private static final char[] CHARS62 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9' };

    private static final char[] CHARS36UPPER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    private static final char[] CHARS36LOWER = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    private static final char[] CHARS_WITHOUT_CONFUSION = { 'a', 'b', 'c', 'd', 'e', 'f',  'h', 'j', 'k', 'm', 'n',
        'o', 'p', 'r', 's', 't', 'w', 'x', 'y', 'z' };

    private RandomIdGenerator() {

    }

    public static RandomIdGenerator getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new RandomIdGenerator();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
//             System.out.println(RandomIdGenerator.getInstance().generateRandomCouponNumber(6));
            RandomIdGenerator.getInstance().generateRandomId(40);
//            RandomIdGenerator.generateRandomIds(40);
//            if (i % 1000000 == 0) {
//                System.out.println("round: " + i);
//            }
        }
        System.out.println(System.currentTimeMillis() - t0);
    }

    /**
     * 產生長度numberOfChar的隨機文字 (a~z+A~Z+0~9) 62個組合
     * 
     * @return random id
     */
    public String generateRandomId(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS62[random.nextInt(62)];
        }
        return new String(output);
    }

    private static final Random RANDOM = new Random();
    public static String generateRandomIds(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS62[RANDOM.nextInt(62)];
        }
        return new String(output);
    }
    
    
    /**
     * 產生長度numberOfChar的隨機文字(A~Z+0~9) 36個組合
     * 
     * @return random id
     */
    public String generateRandomIdUpper36(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS36UPPER[random.nextInt(36)];
        }
        return new String(output);
    }

    public String generateRandomCouponNumber(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0, size = CHARS_WITHOUT_CONFUSION.length; i < numberOfChar; i++) {
            output[i] = CHARS_WITHOUT_CONFUSION[random.nextInt(size)];
        }
        return new String(output);
    }

    /**
     * 產生長度numberOfChar的隨機文字(a~z+0~9) 36個組合
     * 
     * @return random id
     */
    public String generateRandomIdLower36(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS36LOWER[random.nextInt(36)];
        }
        return new String(output);
    }
}
