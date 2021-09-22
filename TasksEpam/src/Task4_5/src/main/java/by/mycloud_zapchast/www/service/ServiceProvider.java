package by.mycloud_zapchast.www.service;


import by.mycloud_zapchast.www.service.impl.ItemServiceImpl;
import by.mycloud_zapchast.www.service.impl.MailSenderImpl;
import by.mycloud_zapchast.www.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider INSTANCE = new ServiceProvider();

	private final UserService USER_SERVICE = new UserServiceImpl();
	private final ItemService ITEM_SERVICE = new ItemServiceImpl();
	private final MailSender MAIL_SENDER=new MailSenderImpl();

	private ServiceProvider() {}
	
	
	public static ServiceProvider getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return USER_SERVICE;
	}

	public ItemService getItemService() {
		return ITEM_SERVICE;
	}
	
	public MailSender getMailSender() {
		return MAIL_SENDER;
	}

}
