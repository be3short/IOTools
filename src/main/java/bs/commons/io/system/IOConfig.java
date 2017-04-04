package bs.commons.io.system;

import java.util.ArrayList;
import java.util.HashMap;

import bs.commons.io.system.IO.MessageCategory;

public class IOConfig
{

	//	public final boolean printSystem;
	//	public final boolean printWarnings;
	//	public final boolean printErrors;
	//	public final boolean printDebug;
	public HashMap<MessageCategory, Boolean> config;
	//public ArrayList<MessageConfig> config;

	public IOConfig()//boolean print_system, boolean print_warnings, boolean print_errors, boolean print_debug)
	{
		//		printSystem = print_system;
		//		printWarnings = print_warnings;
		//		printErrors = print_errors;
		//		printDebug = print_debug;
		config = getDefaultConfig();
		//config = getDefaultConfigList();
	}

	private HashMap<MessageCategory, Boolean> getDefaultConfig()
	{
		HashMap<MessageCategory, Boolean> defaultConfig = new HashMap<MessageCategory, Boolean>();
		for (MessageCategory category : MessageCategory.values())
		{
			if (category.equals(MessageCategory.DEBUG) || category.equals(MessageCategory.DEV))
			{
				defaultConfig.put(category, false);
			} else
			{
				defaultConfig.put(category, true);
			}
		}
		return defaultConfig;
	}

	private ArrayList<MessageConfig> getDefaultConfigList()
	{
		ArrayList<MessageConfig> defaultConfig = new ArrayList<MessageConfig>();
		for (MessageCategory category : MessageCategory.values())
		{
			if (category.equals(MessageCategory.DEBUG) || category.equals(MessageCategory.DEV))
			{
				defaultConfig.add(new MessageConfig(category, false));
			} else
			{
				defaultConfig.add(new MessageConfig(category, true));
			}
		}
		return defaultConfig;
	}

	public IOConfig duplicate()
	{
		return new IOConfig();//printSystem, printWarnings, printErrors, printDebug);
	}

	public boolean printStatus(MessageCategory category)
	{
		return config.get(category);
	}

}
