package com.be3short.io.format;

public interface FileFormat<T extends FileFormat<T>>
{

	public FileToolbox<T> getTools();

	public String getFileExtension();

	public String getFormatName();
}
