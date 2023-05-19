package model;

import java.util.Random;

public class idGen {
    public idGen() {

    }
    
    public String generateUserId() {
        // AAA-0000

        String textPart = "";
        String numbered = "";

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] nums = "1234567890".toCharArray();
        for (int i = 0; i < 3; i++) {
            textPart += chars[random.nextInt(chars.length)];
        }
        for (int i = 0; i < 4; i++) {
            numbered += nums[random.nextInt(nums.length)];
        }

        return (textPart + "-" + numbered);
    }
    
    public String generateOrderId() {
        // AAAAA-00000

        String textPart = "";
        String numbered = "";

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] nums = "1234567890".toCharArray();
        for (int i = 0; i < 5; i++) {
            textPart += chars[random.nextInt(chars.length)];
        }
        for (int i = 0; i < 5; i++) {
            numbered += nums[random.nextInt(nums.length)];
        }

        return (textPart + "-" + numbered);
    }
}
