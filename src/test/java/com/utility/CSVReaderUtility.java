package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {


        File csvFile = new File(System.getProperty("user.dir") + "\\testData\\" + fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        String[] csvFileLine;
        List<User> userList = new ArrayList<>();
        User user;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // skip the col name

            while ((csvFileLine = csvReader.readNext()) != null) {
                user = new User(csvFileLine[0], csvFileLine[1]);
                userList.add(user);
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        return userList.iterator();

    }
}
