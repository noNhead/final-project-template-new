package com.epam.rd.izh.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReaderParser {
    public String fb2ReaderParser(File file){
        FileInputStream fileInputStream;
        StringBuilder stringOut = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer, 0 , fileInputStream.available());
            StringBuilder tag = new StringBuilder();
            for (int i = 0; i < buffer.length; i++) {
                if ((char)buffer[i] == '<'){
                    do {
                        tag.append((char) buffer[i]);
                        i++;
                    } while ((char)buffer[i-1] != '>');
                    if (tag.equals("<p>")){
                        stringOut.append("\n\t");
                    }
                }
                stringOut.append((char)buffer[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
