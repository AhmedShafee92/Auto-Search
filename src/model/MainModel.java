package model;

import conncet.server.analyse.file.ConnectConvertStringToJson;
import conncet.server.analyse.file.ConnectGoogleAPIServer;
import conncet.server.analyse.file.ExcelWriter;
import controller.AnalyseController;
import store.user.data.CreateFoldersCloud;
import store.user.data.StoreUserDataLocal;
import store.user.data.StoreUserDataServer;
import store.user.data.UserManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainModel 
{

    public void attachCV() 
    {
        StoreUserDataLocal.storeCVUserLocal();
    }

    public boolean isCVValid() 
    {
        return StoreUserDataLocal.CheckCVFileExist() && !StoreUserDataLocal.isWordFileCVEmpty();
    }

    public void analyseData(JFrame frame) 
    {
        try {
	            String result = ConnectGoogleAPIServer.analyseUserCVData();
	            int decision = JOptionPane.showOptionDialog(frame, result,
                "AI CV Analysis",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes, Save", "No, Retry"},
                "Yes, Save");
            

	            if (decision == JOptionPane.OK_OPTION) 
	            {
	            	
	            	int userId = Session.getUserId();
	            	String filePath =  StoreUserDataLocal.getPersonaldatapahtcv();
	            	File userCVFile = new File(filePath);
	            	// Here we sending request to the server to save the user analysing data in the DB . 
	            	AnalyseController.sendAnalyseRequest(userId,userCVFile);
	            		    
	            }
	            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
