package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener, ISuiteListener, IInvokedMethodListener
{

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) 
	{
		// Before every method in Test Class 
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) 
	{
		// After every method in Test Class 
	}

	@Override
	public void onStart(ISuite suite) 
	{
		// When <suite> tag starts in testng.xml file
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		//When <suite> tag ends in testng.xml file
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		// When the method with @Test annotation starts
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		// If the Test method is successfully passed
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		//  If the Test method is failed
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// If the Test method is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// If the test case failed but if has multiple assertion and most of them are passing you might want to use this
	}

	@Override
	public void onStart(ITestContext context) 
	{
		// When the <test> tag starts in testng.xml starts
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		// When the <test> tag ends in testng.xml starts
	}

}
