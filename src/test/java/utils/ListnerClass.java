package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import basic.ProjectBasicMethods;

public class ListnerClass extends ProjectBasicMethods implements ITestListener {
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
		String screenShortpath=null;
		String  timestamp;
		try {
			 timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			 screenShortpath=screenshot(result.getMethod().getMethodName()+timestamp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(screenShortpath, "Passed Test Screenshorts");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String screenShortpath=null;
		String timestamp;
		try {
			 timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			 screenShortpath=screenshot(result.getMethod().getMethodName()+timestamp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(screenShortpath, "Failed Test Screenshorts");
	}


}
