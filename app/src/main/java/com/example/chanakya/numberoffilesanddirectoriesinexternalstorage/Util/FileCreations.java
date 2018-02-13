package com.example.chanakya.numberoffilesanddirectoriesinexternalstorage.Util;

import android.os.Environment;

/**
 * Created by chanakya on 2/11/18.
 */

/**
 * This class is used to create files for fileoperations.
 *
 */

public class FileCreations {

    String root_directory;

    String fileName1 ="file1";
    String fileName2 ="file2";
    String fileName3 ="file3";
    String fileName4 ="file4";
    String fileName5 ="file5";
    String fileName6 ="file6";
    String fileName7 ="file7";
    String fileName8 ="file8";
    String fileName9 ="file9";
    String fileName10 ="file10";
    String fileName11 ="file11";
    String fileName12 ="file12";
    String fileName13 ="file13";
    String fileName14 ="file14";
    String fileName15 ="file15";

    String fileOneContent ="Hi how are you";
    String fileTwoContent ="I am fine and hope same with you";
    String fileThreeContent ="We all are fine.Its ok";
    String fileFourContent ="Hi how are you";
    String fileFiveContent ="I am fine and hope same with you";
    String fileSixContent ="We all are fine.Its ok";
    String fileSevenContent ="Hi how are you";
    String fileEightContent ="I am fine and hope same with you";
    String fileNineContent ="We all are fine.Its ok";
    String fileTenContent ="We all are fine.Its ok";
    String fileElevenContent ="We all are fine.Its ok";
    String fileTewelveContent ="Hi how are you";
    String fileThirteenContent ="I am fine and hope same with you";
    String fileFourteenContent ="We all are fine.Its ok";
    String fileFifteenContent ="We all are fine.Its ok";



    public FileCreations(){

    }


    /**
     * This method is used for creating object of 15 files
     */
    public void createFiles() {


     // root directory used for accessing root directory of eternal storage.
    root_directory = Environment.getExternalStorageDirectory().toString();






    FileOperations fileOne = new FileOperations(root_directory);
       fileOne.write(fileName1,fileOneContent);

    FileOperations fileTwo = new FileOperations(root_directory);
       fileTwo.write(fileName2,fileTwoContent);

    FileOperations fileThree = new FileOperations(root_directory);
       fileThree.write(fileName3,fileThreeContent);


        FileOperations fileFour = new FileOperations(root_directory);
        fileFour.write(fileName4,fileFourContent);

        FileOperations fileFive = new FileOperations(root_directory);
        fileFive.write(fileName5,fileFiveContent);

        FileOperations fileSix = new FileOperations(root_directory);
        fileSix.write(fileName6,fileSixContent);

        FileOperations fileSeven = new FileOperations(root_directory);
        fileSeven.write(fileName7,fileSevenContent);

        FileOperations fileEight = new FileOperations(root_directory);
        fileEight.write(fileName8,fileEightContent);

        FileOperations fileNine = new FileOperations(root_directory);
        fileNine.write(fileName9,fileNineContent);

        FileOperations fileTen = new FileOperations(root_directory);
        fileTen.write(fileName10,fileTenContent);

        FileOperations fileEleven = new FileOperations(root_directory);
        fileEleven.write(fileName11,fileElevenContent);

        FileOperations fileTewlve = new FileOperations(root_directory);
        fileTewlve.write(fileName12,fileTewelveContent);

        FileOperations fileThirteen = new FileOperations(root_directory);
        fileThirteen.write(fileName13,fileThirteenContent);

        FileOperations fileFourteen = new FileOperations(root_directory);
        fileFourteen.write(fileName14,fileFourteenContent);

        FileOperations fileFifteen = new FileOperations(root_directory);
        fileFifteen.write(fileName15,fileFifteenContent);






    }







}
