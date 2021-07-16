package com.sandbox.utils;

public class PassportUtil {
    public static long decrypt(long euid) {
        long uid = 0;
        if (euid < 2147483648L) {
            uid = euid;
        } else {
            long seed = euid >>> 32;
            int confuse = getHash((int) seed);
            uid = euid ^ (seed << 32);
            uid = uid ^ getUnsignedInt(confuse);
        }
        return uid;
    }

    public static long encrypt(long uid) {
        long encryuid = 0L;
        int seed = getHash((int) uid);
        seed = seed & 0x0000FFFF;
        long seed64 = seed;
        int confuse = getHash(seed);
        encryuid = seed64 << 32 | uid;
        encryuid = encryuid ^ getUnsignedInt(confuse);
        return encryuid;
    }

    private static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    }

    private static int getHash(int seed) {
        seed -= (seed << 6);
        seed ^= (seed >>> 17);
        seed -= (seed << 9);
        seed ^= (seed << 4);
        seed -= (seed << 3);
        seed ^= (seed << 10);
        seed ^= (seed >>> 15);
        return seed;
    }

    public static void main(String[] args) {
        System.out.println(decrypt(21474836481L));
        System.out.println(encrypt(131587319904673L));
    }
}
