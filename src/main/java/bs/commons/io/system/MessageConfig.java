package bs.commons.io.system;

import bs.commons.io.system.IO.MessageCategory;

public class MessageConfig
{

	public MessageCategory category;
	public Boolean printStatus;

	public MessageConfig(MessageCategory category, Boolean status)
	{
		this.category = category;
		printStatus = status;
	}
}
