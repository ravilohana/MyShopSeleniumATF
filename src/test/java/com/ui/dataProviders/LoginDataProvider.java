package com.ui.dataProviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() {

        Gson gson = new Gson();
        File jsonDataFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + "user_login_data.json");
        FileReader fileReader = null;
        List<Object[]> dataToBeReturn = new ArrayList<>();
        try {
            fileReader = new FileReader(jsonDataFile);
            TestData testData = gson.fromJson(fileReader, TestData.class);

            for (User user : testData.getData()) {
                dataToBeReturn.add(new Object[]{user});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return dataToBeReturn.iterator();

    }


    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){
       return CSVReaderUtility.readCSVFile("user_login_data.csv");

    }

    @DataProvider(name = "LoginTestXLSXDataProvider")
    public Iterator<User> loginXLSXDataProvider(){
        return ExcelReaderUtility.readExcelFile("user_login_data.xlsx");

    }


}
