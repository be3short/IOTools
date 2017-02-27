package bs.commons.io.system;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bs.commons.objects.identification.MethodIdentifier;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class IO
{

	public static enum MessageCategory
	{
		OUTPUT(
			""),
		SYSTEM(
			"[System]"),
		DEBUG(
			"[Debug]"),
		ERROR(
			"[Error]"),
		WARN(
			"[Error]"),
		DEV(
			"[Dev]");
		;

		public final String label;

		private MessageCategory(String new_label)
		{
			label = new_label;
		}
	}

	private static HashMap<MessageCategory, PrintStream> printLocations = initializePrintLocations();
	//	private static HashMap<MessageCategory, Boolean> printEnabled;

	public static IOSettings settings = new IOSettings(IO.class, "bs");

	public static void setSettings(IOSettings set)
	{
		if (set == null)
		{
			settings = new IOSettings(IO.class, "bs");
		} else
		{
			settings = set;
		}

	}

	private static HashMap<MessageCategory, PrintStream> initializePrintLocations()
	{
		HashMap<MessageCategory, PrintStream> locations = new HashMap<MessageCategory, PrintStream>();
		for (MessageCategory category : MessageCategory.values())
		{
			if (category.equals(MessageCategory.ERROR))
			{
				locations.put(category, System.err);
			} else
			{
				locations.put(category, System.out);
			}
		}
		return locations;
	}

	public static void setPrintLocation(MessageCategory category, PrintStream location)
	{
		try
		{
			printLocations.put(category, location);
		} catch (Exception badLocation)
		{
			err(badLocation, "Invalid print location or category");
		}
	}

	public static void sys(String line)
	{

		println(line, MessageCategory.SYSTEM);
	}

	public static void out(String line)
	{
		println(line, MessageCategory.OUTPUT);
	}

	public static void println(String line, MessageCategory category)
	{
		println(line, category, 0);
	}

	public static void println(String line, MessageCategory category, Integer increment)
	{
		String newLine = supplementLine(line, category, increment);
		printLocations.get(category).println(newLine);
	}

	public static void debug(String line)
	{
		println(line, MessageCategory.DEBUG);
	}

	public static void err(Throwable error)
	{
		err(error, null);
	}

	public static void err(Throwable error, String message)
	{

		error.printStackTrace(printLocations.get(MessageCategory.ERROR));
		String reason = "";
		if (message != null)
		{
			reason = '\n' + "Reason : " + message;
		}
		String newLine = "Error: " + error.getMessage() + reason;
		println(newLine, MessageCategory.ERROR);
		println(newLine, MessageCategory.OUTPUT);
	}

	public static void warn(String line)
	{
		println("Warning : " + line, MessageCategory.OUTPUT);
	}

	public static void dev(String line)
	{
		println(line, MessageCategory.DEV);
	}

	//	private static boolean classFilter(MessageCategory category)
	//	{
	//		boolean filter = true;
	//		if (settings.enableClassDebugFiltering)
	//		{
	//
	//			try
	//			{
	//				if (settings.debugClasses.getMap().size() > 0 && settings.devClasses.getMap().size() > 0)
	//				{
	//					HashMap<Object, Boolean> classMap = null;
	//					switch (category)
	//					{
	//					case DEBUG:
	//						classMap = settings.debugClasses.getMap();
	//						break;
	//					case DEV:
	//						classMap = settings.devClasses.getMap();
	//						break;
	//					}
	//
	//					Class classPackage;
	//					try
	//					{
	//						classPackage = ClassLoader.getSystemClassLoader()
	//						.loadClass(MethodIdentifier.getCallerCallerClassNameOnly());
	//
	//						if (classMap.containsKey(classPackage))
	//						{
	//							filter = classMap.get(classPackage).booleanValue();
	//
	//						}
	//					} catch (ClassNotFoundException e)
	//					{
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//
	//				} else
	//				{
	//					filter = false;
	//				}
	//			} catch (Exception notInitialized)
	//			{
	//			}
	//		}
	//		return filter;
	//	}
	private static String supplementLine(String line, MessageCategory category)
	{
		return supplementLine(line, category, 0);
	}

	private static String supplementLine(String line, MessageCategory category, Integer increment)
	{
		if (settings.supplementMessages)
		{
			String supLine = "";
			if (settings.printMessageCategory)
			{
				supLine += category.label;
			}
			if (settings.printDate)
			{

				supLine += "[" + StringFormatter.getCurrentDateString(System.currentTimeMillis() / 1000, "/", false)
				+ "]";
			}
			if (settings.printTime)
			{
				supLine += "[" + StringFormatter.getAbsoluteHHMMSS() + "]";
			}
			if (settings.printCallingClass)
			{

				//String[] classPackage = MethodIdentifier.getCallerClassName(1).split("\\$")[0].split("\\.");
				//String className = classPackage[classPackage.length - 1];
				//if (!className.equals("CustomPrinterRuntimeTest"))
				//{
				//	supLine += "[" + classPackage[classPackage.length - 1] + "]";
				//}
			}
			if (supLine.length() > 0)
			{
				supLine += " ";
			}
			return supLine + line;
		} else
		{
			return line;
		}
	}

	public static void rerouteSystemOutput()
	{
		OutputStream sys = new ExtendedOutput(MessageCategory.SYSTEM, 2);
		System.setOut(new PrintStream(sys, true));
		OutputStream err = new ExtendedOutput(MessageCategory.ERROR, 2);
		System.setErr(new PrintStream(err, true));
	}

}
