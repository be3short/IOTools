package bs.commons.io.system;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import bs.commons.io.system.IO.MessageCategory;
import javafx.application.Platform;

public class ExtendedOutput extends OutputStream
{

	private String line;
	private MessageCategory category;
	private Integer stackIncrement;

	public ExtendedOutput(MessageCategory category, Integer stack_increment)
	{
		stackIncrement = stack_increment;
		this.category = category;
	}

	public void appendText(String valueOf) throws IOException
	{
		line += valueOf;

	}

	public void printLine()
	{
		IO.println(line, category);
		line = "";
	}

	@Override
	public void write(int b) throws IOException
	{

		char ch = (char) b;
		if ('\n' == ch)
		{
			printLine();
		} else
		{
			appendText(String.valueOf(ch));
		}

	}

}
