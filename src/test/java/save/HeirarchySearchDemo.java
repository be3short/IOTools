package save;

import java.lang.reflect.Field;

public class HeirarchySearchDemo
{

	public static class DemoClass
	{

		public static String staticItem;
		public String instanceItem;

		public DemoClass()
		{
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
		DemoClass saver = new DemoClass();
		System.out.println(printSubElements(saver));
	}

	public static String printSubElements(Object obj)
	{
		String list = "Root: " + obj.toString() + "\n";
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			try
			{
				//field.setAccessible(true);
				Object subObject = field.get(obj);
				list += "Sub: " + subObject.toString() + "\n";
				list += printSubElements(subObject);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
