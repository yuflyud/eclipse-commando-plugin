package com.softserveinc.eclipsecommando.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {
    public static void inToOutStream(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
    }
}
