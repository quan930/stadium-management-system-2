package app.mrquan.util;

import java.io.*;

public class DataProcessing {
    public static byte[] toPrimitives(Byte[] oBytes){
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }
    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }
    public static Byte[] fileToBytes(String pathName) throws IOException {
        byte[] headOne;
        File file = new File(pathName);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
        byte[] b = new byte[1000];
        int n;
        while ((n = fis.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        headOne = bos.toByteArray();
        Byte[] headTwo = new Byte[headOne.length];
        int i = 0;
        for (byte c : headOne) headTwo[i++] = c;
        return headTwo;
    }
    public static void bytesToFile(Byte[] bu,String pathname) throws IOException {
        byte[] bytes = new byte[bu.length];
        for(int j = 0; j < bu.length; j++) {
            bytes[j] = bu[j];
        }
        BufferedOutputStream bos = null;
        bos = new BufferedOutputStream(new FileOutputStream(new File(pathname)));
        bos.write(bytes);
        bos.flush();
        bos.close();
    }
}