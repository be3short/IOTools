package com.be3short.io.format;

import java.io.File;

public interface FileFormat
{

	public String getFileExtension();

	public String getFormatName();

	public <F extends FileFormat> F getDefaultFormat();

	public static <F extends FileFormat> boolean isFileFormat(File file, F format)
	{
		Integer extensionLength = format.getFileExtension().length();

		if (file.getName().length() > extensionLength)
		{
			if (file.getName().substring(extensionLength - 1).contains(format.getFileExtension()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a file name is appended with the corresponding extension
	 * 
	 * @param <T>
	 * 
	 * @param file
	 *            location of file to check
	 * @param extension
	 *            extension label of file type
	 * @return true if the file contains the extension, and false otherwise
	 *
	 */
	public static <T extends FileFormat> T checkFormat(File file)
	{
		T matching = null;
		if (matching == null)
		{
			checkFormat(file);
		}
		T[] allFormats = (T[]) matching.getClass().getEnumConstants();
		for (T fileFormat : allFormats)
			try
			{
				Integer extLength = fileFormat.getFileExtension().length();
				if (file.getName().length() > extLength)
				{
					if (file.getName().substring(extLength).contains(fileFormat.getFileExtension()))
					{
						matching = fileFormat;
					}
				}
			} catch (Exception badFile)
			{
				System.err.println("Unable to determine what format the file " + file + " was");

			}
		return matching;

	}

	/**
	 * Checks if a file name is appended with the corresponding extension
	 * 
	 * @param file
	 *            location of file to check
	 * @param extension
	 *            extension label of file type
	 * @return true if the file contains the extension, and false otherwise
	 *
	 */
	public static boolean hasExtensionAppended(File file, String extension)
	{
		try
		{
			if (file.getName().length() > extension.length())
			{
				if (file.getName().contains(extension))
				{
					return true;
				}
			}
		} catch (Exception badFile)
		{
			System.err.println("Unable to determine if file " + file + " format was " + extension);
		}
		return false;
	}

	/**
	 * Adds the necessary extension to a file if it has not already been
	 * included.
	 * 
	 * @param file
	 *            location of file to check
	 * @param extension
	 *            extension label of file type
	 * @return the file with the appended extension
	 *
	 */
	public static File getExtensionAppended(File file, String extension)
	{
		File adjustedFile = file;
		try
		{

			Boolean needsExtension = hasExtensionAppended(file, extension);
			if (needsExtension)
			{
				adjustedFile = new File(file.getAbsoluteFile() + extension);
			}
		} catch (

		Exception badFile)
		{
			System.out.println("Unable to determine if file " + file + " contained extension " + extension);
		}
		return adjustedFile;

	}
}
