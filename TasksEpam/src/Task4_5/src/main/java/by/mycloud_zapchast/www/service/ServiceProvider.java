package by.mycloud_zapchast.www.service;


import by.mycloud_zapchast.www.service.impl.ItemServiceImpl;
import by.mycloud_zapchast.www.service.impl.MailSender;
import by.mycloud_zapchast.www.service.impl.MailSenderImpl;
import by.mycloud_zapchast.www.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private final UserService userService = new UserServiceImpl();
	private final ItemService itemService = new ItemServiceImpl();
	private final MailSender mailSender=new MailSenderImpl();

	private ServiceProvider() {}
	
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ItemService getItemService() {
		return itemService;
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

}
