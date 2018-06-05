package com.example.first.testandroid;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOperationsHelper {
    private static FileOperationsHelper operationsHelper;

    public static FileOperationsHelper getInstance() {
        if (operationsHelper == null) {
            operationsHelper = new FileOperationsHelper();
        }
        return operationsHelper;
    }



    private FileOperationsHelper() {
    }


    public void saveFile( Context context,String fileName, String data) throws IOException {
        FileOutputStream fileOutputStream=context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }

    public String readFile(Context context,String fileName) throws IOException {
        FileInputStream fileInputStream=context.openFileInput(fileName);
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


        StringBuffer stringBuffer=new StringBuffer();
        String line;
        while ((line=bufferedReader.readLine())!=null){
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }
}

