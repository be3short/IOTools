package com.be3short.io.format;

import java.io.File;

public class FileToolbox<F extends FileFormat<F>>
{

	F fileFormat;

	public FileToolbox(F format)
	{
		this.fileFormat = format;
	}

	public <F> boolean isFileFormat(File file)
	{
		Integer extensionLength = fileFormat.getFileExtension().length();

		if (file.getName().length() > extensionLength)
		{
			if (file.getName().substring(extensionLength - 1).contains(fileFormat.getFileExtension()))
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
	@SuppressWarnings(
	{ "unchecked", "null" })
	public static <T extends FileFormat<T>> T checkFormat(File file)
	{
		T matching = null;
		if (matching == null)
		{
			checkFormat(file);
		}
		T[] allFormats = (T[]) matching.getClass().getEnumConstants();
		for (T fileformat : allFormats)
			try
			{
				Integer extLength = fileformat.getFileExtension().length();
				if (file.getName().length() > extLength)
				{
					if (file.getName().substring(extLength).contains(fileformat.getFileExtension()))
					{
						matching = fileformat;
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
	 * @return true if the file contains the extension, and false otherwise
	 *
	 */
	public boolean hasExtensionAppended(File file)
	{
		try
		{
			if (file.getName().length() > fileFormat.getFileExtension().length())
			{
				if (file.getName().contains(fileFormat.getFileExtension()))
				{
					return true;
				}
			}
		} catch (Exception badFile)
		{
			System.err.println("Unable to determine if file " + file + " format was " + fileFormat.getFileExtension());
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
	public File getExtensionAppended(File file)
	{
		File adjustedFile = file;
		try
		{

			Boolean needsExtension = hasExtensionAppended(file);
			if (needsExtension)
			{
				adjustedFile = new File(file.getAbsoluteFile() + fileFormat.getFileExtension());
			}
		} catch (

		Exception badFile)
		{
			System.out
			.println("Unable to determine if file " + file + " contained extension " + fileFormat.getFileExtension());
		}
		return adjustedFile;

	}

}
