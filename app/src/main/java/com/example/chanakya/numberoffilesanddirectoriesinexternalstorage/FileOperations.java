package com.example.chanakya.numberoffilesanddirectoriesinexternalstorage;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by chanakya on 2/10/18.
 */

/**
 * This class is used for File operations.
 * Basic File read, write operations are done here.
 */

public class FileOperations {

    private final String root_directory; // root directory for accessing external storage

    //constructor for file performing operations
    public FileOperations(String root_directory){
this.root_directory=root_directory;
    }

    public boolean write(String fname, String fContent){

        try {
            File dir = new File(root_directory+"/SDCARD/");
            if(!dir.exists()) {
                dir.mkdir();
            }
            String filename =  fname + ".txt";

            File file = new File(dir,filename);


            if (!file.exists()) {

                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(fContent.getBytes());

            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(fContent);
            bufferedWriter.close();

            Log.d("Success","File Creation Success"+file.exists());

            return true;

        }

        catch(IOException e){
            e.printStackTrace();
            return false;
        }


    }

    /**
     * This method is used for read operations from a file with
     * @param fname   which is String representing file name
     * @return
     */
    public String read(String fname){

        BufferedReader br;
        String response ;

        try{

            StringBuffer output = new StringBuffer();
            String fpath = "/sdcard/"+fname+".txt";

            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            response = output.toString();

        }
        catch(IOException e){
            e.printStackTrace();
            return  null;
        }


        return response;
    }



}
