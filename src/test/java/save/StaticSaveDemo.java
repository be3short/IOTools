package save;

import java.util.HashMap;

import bs.commons.objects.manipulation.XMLParser;

public class StaticSaveDemo
{

	public static class GlobalClass
	{

		HashMap<Integer, DemoClass> classes = new HashMap<Integer, DemoClass>();

		public GlobalClass()
		{
			for (Integer i = 0; i < 10; i++)
			{
				DemoClass d = new DemoClass(this);
				classes.put(i, d);
			}
		}
	}

	public static class DemoClass
	{

		private GlobalClass globe;
		public static String staticItem;
		public String instanceItem;

		public DemoClass(GlobalClass globe)
		{
			this.globe = globe;
			staticItem = "Static";
			instanceItem = "Instance";
		}

		public boolean instanceMethod()
		{
			return false;
		}

		public static boolean staticMethod()
		{
			return false;
		}
	}

	public static void main(String args[])
	{
		GlobalClass saver = new GlobalClass();
		System.out.println(XMLParser.serializeObject(saver.classes.get(5)));
	}
}
