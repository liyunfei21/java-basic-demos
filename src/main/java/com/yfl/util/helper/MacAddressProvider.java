//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yfl.util.helper;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;

public class MacAddressProvider {
    public MacAddressProvider() {
    }

    private static byte[] getMacAddress() throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        if (en != null) {
            while (en.hasMoreElements()) {
                NetworkInterface nint = (NetworkInterface) en.nextElement();
                if (!nint.isLoopback()) {
                    byte[] address = nint.getHardwareAddress();
                    if (isValidAddress(address)) {
                        return address;
                    }
                }
            }
        }

        return null;
    }

    private static boolean isValidAddress(byte[] address) {
        if (address != null && address.length == 6) {
            byte[] var1 = address;
            int var2 = address.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                byte b = var1[var3];
                if (b != 0) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static byte[] getSecureMungedAddress() {
        byte[] address = null;

        try {
            address = getMacAddress();
        } catch (SocketException var3) {
        }

        if (!isValidAddress(address)) {
            address = constructDummyMulticastAddress();
        }

        byte[] mungedBytes = new byte[6];
        SecureRandomHolder.INSTANCE.nextBytes(mungedBytes);

        for (int i = 0; i < 6; ++i) {
            mungedBytes[i] ^= address[i];
        }

        return mungedBytes;
    }

    private static byte[] constructDummyMulticastAddress() {
        byte[] dummy = new byte[6];
        SecureRandomHolder.INSTANCE.nextBytes(dummy);
        dummy[0] = (byte) (dummy[0] | 1);
        return dummy;
    }

    static class SecureRandomHolder {
        public static final SecureRandom INSTANCE = new SecureRandom();

        SecureRandomHolder() {
        }
    }

}
