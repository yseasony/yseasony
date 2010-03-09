package com.yy.test.base;

import java.util.Random;

public class Prisoner {   
	   /**  
	    * 犯人数目  
	    */  
	   private static final int PRISONER_COUNT = 100;   
	 
	   /**  
	    * @param args  
	    */  
	    public static void main(String[] args) {   
	        Random r = new Random();   
	        boolean lightStartState = r.nextBoolean(); // 被关进去时看到灯的状态（假设被关进去时都经过院子）   
	        boolean lightState = lightStartState; // 当前灯的状态   
	        boolean[] prisoner = new boolean[PRISONER_COUNT]; // 犯人，当出来放过风并且按照约定改变灯过的状态时，其值为true   
	        final int COUNTER_ID = r.nextInt(PRISONER_COUNT); // 任意指定计数员   
	        int counter = 0; // 计数员记下的出来放过风的犯人数目   
	        int days = 0; // 总共所花的天数   
	        while (counter < PRISONER_COUNT) {   
	            days++;   
	            int n = r.nextInt(PRISONER_COUNT); // 随机出来放风的犯人   
	            if (n == COUNTER_ID) {// 计数员   
	                if (!prisoner[COUNTER_ID]) {   
	                    counter++;   
	                    prisoner[COUNTER_ID] = true; // 计数员放过风   
	                }   
	                if (lightState != lightStartState) { // 灯非初始状态   
	                    // 是有其他犯人出来放过风了   
	                    lightState = lightStartState; // 将灯改回初始状态   
	                    counter++;   
	                }   
	            } else { // 其他犯人   
	                if (!prisoner[n] && lightState == lightStartState) { // 犯人未改过灯的状态，且目前灯的状态和初始状态一致   
	                    // 改变灯的状态，以告诉计数员他出来过   
	                    lightState = !lightStartState;   
	                    prisoner[n] = true;   
	                }   
	            }   
	        }   
	        // 检查结果   
	        int sum = 0;   
	        for (int i = 0; i < PRISONER_COUNT; i++) {   
	            if (prisoner[i]) {   
	                sum++;   
	            }   
	        }   
	        System.out.println(sum + "个犯人出来放过风");   
	        System.out.println("共花了 " + days + "天（" + days / 365 + "年）");   
	    }   
	} 
