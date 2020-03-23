package com.boyue.server.service;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(new int[]{1, 2, 3, 4, 5});
        for (int i=0;i<5;i++){
            System.out.print(intBuffer.get(i));
        }
        intBuffer.flip();//读写切换 坐标对调
        intBuffer.put(6);
        System.out.println();
        for (int i=0;i<5;i++){
            System.out.print(intBuffer.get(i));
        }
    }
}
