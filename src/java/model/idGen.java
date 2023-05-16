/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author Joseph Robles
 */
public class idGen {
    
    public idGen()
	{

	}
    
    public String generateUserId()
    {
        // AAA-0000

        String textPart = "";
        String numbered = "";

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] nums = "1234567890".toCharArray();
        for (int i = 0; i < 3; i++)
        {
            textPart += chars[random.nextInt(chars.length)];
        }
        for (int i = 0; i < 4; i++)
        {
            numbered += nums[random.nextInt(nums.length)];
        }

        return (textPart + "-" + numbered);
    }
}
