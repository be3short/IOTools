package bs.commons.io.system;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;

public class RoutedOutput extends OutputStream
{

	protected final boolean threadded;

	public RoutedOutput(boolean threadded)
	{
		this.threadded = threadded;
	}

	public void appendText(String valueOf) throws IOException
	{
		if (threadded)
		{
			Platform.runLater(() -> System.out.print(valueOf));
		} else
		{
			System.out.print(valueOf);
		}
	}

	@Override
	public void write(int b) throws IOException
	{
		if (threadded)
		{
			Platform.runLater(() ->
			{
				try
				{
					appendText(String.valueOf((char) b));
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} else
		{
			appendText(String.valueOf((char) b));
		}

	}

}
