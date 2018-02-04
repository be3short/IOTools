package com.be3short.io.format;

public enum ImageFormat implements FileFormat<ImageFormat>
{

	PNG(
		"PNG",
		".png",
		true,
		true),
	JPEG(
		"JPEG",
		".jpg",
		true,
		true),
	GIF(
		"gif",
		".gif",
		true,
		true),
	BMP(
		"BMP",
		".bmp",
		true,
		true),
	SVG(
		"SVG",
		".svg",
		false),
	EPS(
		"EPS",
		".eps",
		false);

	private final FileToolbox<ImageFormat> toolbox;
	public final String extension;
	public final String name;
	public final boolean image;
	public final boolean needsBackground;

	private ImageFormat(String name, String extension, boolean image)
	{
		this.name = name;
		this.image = image;
		this.needsBackground = false;
		this.extension = extension;
		toolbox = new FileToolbox<ImageFormat>(this);
	}

	private ImageFormat(String name, String extension, boolean image, boolean background)
	{
		this.name = name;
		this.image = image;
		this.needsBackground = background;
		this.extension = extension;
		toolbox = new FileToolbox<ImageFormat>(this);

	}

	public static ImageFormat getMatch(String name)
	{
		ImageFormat match = null;
		for (ImageFormat format : ImageFormat.values())
		{
			if (format.name.toLowerCase().contains(name.toLowerCase()))
			{
				match = format;
				break;
			}
		}
		return match;
	}

	@Override
	public String getFileExtension()
	{
		// TODO Auto-generated method stub
		return this.extension;
	}

	@Override
	public String getFormatName()
	{
		// TODO Auto-generated method stub
		return this.name();
	}

	@Override
	public FileToolbox<ImageFormat> getTools()
	{
		// TODO Auto-generated method stub
		return toolbox;
	}

}