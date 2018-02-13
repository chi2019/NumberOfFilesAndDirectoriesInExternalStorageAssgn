package com.example.chanakya.numberoffilesanddirectoriesinexternalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chanakya.numberoffilesanddirectoriesinexternalstorage.Util.FileCreations;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class has three functionalities for
 * showing top 10 large files.
 * showing average file size
 * showing frequencies of extension files
 */


public class MainActivity extends AppCompatActivity {

    String root_directory;
    StringBuffer filesListExtensions = new StringBuffer("");
    StringBuffer filesListNames = new StringBuffer("");
    StringBuffer filesListLengths = new StringBuffer("");
    Map<String,Integer> filesDetails = new TreeMap<String,Integer>(Collections.reverseOrder());
    static int average;
    static int sum;
    StringBuffer result = new StringBuffer(" ");
    StringBuffer fileFrequencies = new StringBuffer("");

    File file;

    Button showFirst10FilesWithSizesButton,showAverageFilesizeButton,showFileFrequenciesButton;
    TextView resultDisplayTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        average =0; // used to calculate the average size of file
        sum=0; // used to calculate the sum of frequencies of all files

        resultDisplayTextView = findViewById(R.id.resultDisplayTextView);
        showFirst10FilesWithSizesButton = findViewById(R.id.showFirst10FilesWithSizesButton);
        showFileFrequenciesButton = findViewById(R.id.showFileFrequenciesButton);
        showAverageFilesizeButton = findViewById(R.id.showAverageFilesizeButton);


        /**
         * Here we are checking if permissions are granted  or not.
         * And asking runtime permissions
         */
        getPermissions();


        /**
         * Files creation for performing basic operations
         */

        FileCreations fileCreations = new FileCreations();
        fileCreations.createFiles();

        // root directory for accessing external storage path
        root_directory = Environment.getExternalStorageDirectory().toString();


        file = new File(root_directory  );

        filesListNames = listAllFiles(file);
        filesListLengths = lengthOfAllFiles(file);
        filesListExtensions = extensionsOfALlFiles(file);
        fileFrequencies = getFrequencies(filesListExtensions);

        filesDetails = detailsOfAllFiles(file);


        result =  result(filesDetails);

        average = sum / filesListLengths.length();


        /**
         * OnClickListner for showing top 10 files in size
         */

        showFirst10FilesWithSizesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultDisplayTextView.setText(result.toString());
            }
        });

        /**
         * OnClickListner for showing average size of file
         */

        showAverageFilesizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultDisplayTextView.setText(String.valueOf(average));
            }
        });

        /**
         * Showing frequencies of top 5 files
         */

        showFileFrequenciesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               resultDisplayTextView.setText(fileFrequencies.toString());
            }
        });

    }

    private StringBuffer getFrequencies(StringBuffer filesListExtensions) {

     Set<String> extensionsPresent = new HashSet<String>();
     String[] extensions = filesListExtensions.toString().split(" ");

     int n=0;

     Map<String,Integer> count=new TreeMap<String, Integer>(Collections.reverseOrder());
     for(int i=0 ;i<extensions.length;i++){
         extensionsPresent.add(extensions[i]);
     }

     for(String temp:extensionsPresent){
        count.put(temp,0);
     }

     int j=0;

        for(String obj: extensions) {
            if (count.containsKey(obj)) {
                count.put(obj, count.get(obj) + 1);
            }
        }


        for(String key:count.keySet()){
             if(n<5) {
                 fileFrequencies.append(key + "...frequencies:" + count.get(key) + "\n");
                 n++;
             }
          }







        return fileFrequencies;
    }




    private StringBuffer result(Map<String,Integer> filesDetails) {

        StringBuffer temp = new StringBuffer("");

        Set set = filesDetails.entrySet();
        Iterator i = set.iterator();

        int count =0;
        int max =10;

        while(i.hasNext()) {
            if(count<max) {
                Map.Entry me = (Map.Entry) i.next();

                temp.append(me.getKey().toString() + "..." + me.getValue().toString());
                temp.append("\n");
                count++;
            }
            else break;
        }



        return temp;
    }


    private StringBuffer extensionsOfALlFiles(File file) {


        File[] filesAndDir = file.listFiles();

        for(File object: filesAndDir) {

            if (object.isDirectory()) {
                extensionsOfALlFiles(object);
            } else {
                      String extension = getFileExtension(object);

                         filesListExtensions.append(extension);
                         filesListExtensions.append(" ");

            }
        }


        return filesListExtensions;
    }



    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }



    private Map<String,Integer> detailsOfAllFiles(File file) {

        File[] filesAndDir = file.listFiles();

        for(File object: filesAndDir) {

            if (object.isDirectory()) {
                detailsOfAllFiles(object);
            } else {
                filesDetails.put(object.toString(),(int) object.length());

            }
        }

        return filesDetails;
    }

    private StringBuffer lengthOfAllFiles(File file) {

        File[] filesAndDir = file.listFiles();

        for(File object: filesAndDir) {

            if (object.isDirectory()) {
                lengthOfAllFiles(object);
            } else {
                 sum = sum + (int)object.length();
                filesListLengths.append(object.length());
                filesListLengths.append(" ");


            }
        }

      return filesListLengths;
    }


    private StringBuffer listAllFiles(File file) {

        File[] filesAndDir = file.listFiles();

        for(File object: filesAndDir){

            if(object.isDirectory()){
                listAllFiles(object);
            }

            else {
                filesListNames.append(object.toString());
                filesListNames.append(" ");

            }

        }


        return filesListNames;
    }






    private void getPermissions() {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                &&ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                ) {
            Log.e("test", String.valueOf(Environment.getExternalStorageDirectory()));
            Log.e("test", Environment.getExternalStorageDirectory().toString());
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 406);
            }
        }

    }






}
