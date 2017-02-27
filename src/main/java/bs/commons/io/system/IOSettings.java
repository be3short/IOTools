package bs.commons.io.system;

import java.util.ArrayList;
import java.util.Arrays;

public class IOSettings
{

	// Print to io console settings
	public boolean printSystemMessages = true;
	public boolean printDebugMessages = true;
	public boolean printErrorMessages = true;
	public boolean printDevMessages = true;
	public boolean printProblemMessages = false;

	// Print to file settings
	public boolean printSystemMessagesToFile = false;
	public boolean printDebugMessagesToFile = false;
	public boolean printDevMessagesToFile = false;
	public boolean printErrorMessagesToFile = false;
	public boolean printProblemMessagesToFile = false;
	public boolean printAllMessagesToSameFile = false;

	// Print to GUI console settings
	public boolean printToGUIConsole = false;

	// Message format settings
	public boolean supplementMessages = true;
	public boolean printTime = true;
	public boolean printDate = true;
	public boolean printCallingClass = true;
	public boolean printMessageCategory = false;
	private Class mainClass;
	// Filtering
	public boolean enableClassDebugFiltering = true;
	public ArrayList<String> classSearchTerms = new ArrayList<String>(Arrays.asList(new String[]
	{ "bs" }));
	// public HashMap<Object, Boolean> debugPrintClasses;
	// public HashMap<Object, Boolean> devPrintClasses;

	public IOSettings(Class main_class, String... class_terms)
	{
		mainClass = main_class;
		classSearchTerms.addAll(Arrays.asList(class_terms));
		IO.setSettings(this);
		initialize();
	}

	public IOSettings(IOSettings set)
	{
		IO.setSettings(this);
		initialize();
	}

	public IOSettings()
	{
		initialize();
	}

	private void initialize()
	{

	}

	public String[] getClassSearchTerms()
	{
		return classSearchTerms.toArray(new String[classSearchTerms.size()]);
	}

	// public boolean checkForNewClasses()
	// {
	// boolean newClasses = false;
	// if (classSearchTerms.size() > 0)
	// {
	// ArrayList<Class> packageClasses =
	// ClassUtilities.getClassesListFromPackageSection(IOSettings.class,
	// classSearchTerms.toArray(new String[classSearchTerms.size()]));
	// for (Class packageClass : packageClasses)
	// {
	// if (!devClasses..containsKey(packageClass))
	// {
	// debugPrintClasses.put(packageClass, true);
	//
	// newClasses = true;
	// }
	// if (!devPrintClasses.containsKey(packageClass))
	// {
	// devPrintClasses.put(packageClass, true);
	// newClasses = true;
	// }
	// }
	// }
	// return newClasses;
	// }
}
