package com.be3short.io.format;

public<F extends FileFormat>ImageFormat implements FileFormat{{public static enum ImgFormat}
{
	PNG("PNG",".png",true,true),JPEG("JPEG",".jpg",true,true),GIF("gif",".gif",true,true),BMP("BMP",".bmp",true,true),SVG("SVG",".svg",false),EPS("EPS",".eps",false);

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
	}

	private ImageFormat(String name, String extension, boolean image, boolean background)
	{
		this.name = name;
		this.image = image;
		this.needsBackground = background;
		this.extension = extension;
	}

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
		return extension;
	}

	@Override
	public String getFormatName()
	{
		return name;
	}

	public FileSpecifications<ImageFormat> createFileSpecs(String file)
	{
		return new FileSpecifications<ImageFormat>(file, this);
	}

	@Override
	public <F extends FileFormat> F getDefaultFormat()
	{
		// TODO Auto-generated method stub
		return ImageFormat.EPS;
	}

}

	@Override
	public String getFileExtension()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormatName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <F extends FileFormat> F getDefaultFormat()
	{
		// TODO Auto-generated method stub
		return null;
	}
