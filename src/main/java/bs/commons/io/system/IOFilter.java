package bs.commons.io.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import bs.commons.io.file.FileSystemOperator;
import bs.commons.io.system.IO.MessageCategory;
import bs.commons.objects.manipulation.XMLParser;

public class IOFilter
{

	private HashMap<Class, IOConfig> ioStatus;
	private IOConfig defaultConfig;

	IOFilter()
	{
		ioStatus = new HashMap<Class, IOConfig>();
		defaultConfig = new IOConfig();//true, true, true, false);
	}

	protected static IOFilter loadAndUpdateFilter(String config_path, String... packages)
	{

		IOFilter importedFilter = importIOFilter(config_path);
		createFilterFile(config_path, importedFilter, true);
		ArrayList<Class<? extends Object>> classes = getAllClassesPrefix(packages);
		for (Class potentialClass : classes)
		{
			if (!importedFilter.ioStatus.containsKey(potentialClass))
			{
				importedFilter.ioStatus.put(potentialClass, importedFilter.defaultConfig.duplicate());
			}
		}
		createFilterFile(config_path, importedFilter, false);
		return importedFilter;

	}

	public static ArrayList<Class<? extends Object>> getAllClassesPrefix(String... packages)
	{
		ArrayList<Class<? extends Object>> returnClasses = new ArrayList<Class<? extends Object>>();
		for (String packageName : packages)
		{
			List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
			classLoadersList.add(ClasspathHelper.contextClassLoader());
			classLoadersList.add(ClasspathHelper.staticClassLoader());

			Reflections reflections = new Reflections(new ConfigurationBuilder()
			.setScanners(
			new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
			.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
			.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
			Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
			for (Class potentialClass : classes)
			{
				if (!returnClasses.contains(potentialClass))
				{
					returnClasses.add(potentialClass);
				}
			}
		}
		return returnClasses;
	}

	private static void createFilterFile(String config_path, IOFilter filter, boolean is_backup)
	{

		String filterParsed = XMLParser.serializeObject(filter);
		if (is_backup)
		{
			FileSystemOperator.createOutputFile("." + config_path + "_backup", filterParsed);
		} else
		{
			FileSystemOperator.createOutputFile(config_path, filterParsed);
		}
	}

	public static ArrayList<Class<? extends Object>> getAllClasses(String... packages)
	{
		ArrayList<Class<? extends Object>> classes = new ArrayList<Class<? extends Object>>();
		for (String packageName : packages)
		{
			Reflections reflections = new Reflections(packageName);
			classes.addAll(reflections.getSubTypesOf(Object.class));
		}
		return classes;
	}

	public static IOFilter importIOFilter(String config_path)
	{
		try
		{
			IOFilter loadedFilter = (IOFilter) XMLParser.getObject(config_path);
			if (loadedFilter != null)
			{
				return loadedFilter;
			} else
			{
				return new IOFilter();
			}
		} catch (Exception badFile)
		{
			badFile.printStackTrace();
			return new IOFilter();
		}
	}

	public IOConfig getConfig(Class config_class)
	{
		return ioStatus.get(config_class);
	}

	public boolean printStatus(Class config_class, MessageCategory category)
	{
		boolean status = true;
		try
		{
			status = ioStatus.get(config_class).printStatus(category);
		} catch (Exception noFilter)
		{
			//noFilter.printStackTrace();
			//System.out.println(config_class);
		}
		return status;
	}

}
