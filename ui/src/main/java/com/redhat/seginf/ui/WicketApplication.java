package com.redhat.seginf.ui;

import org.apache.wicket.protocol.http.WebApplication;

import com.redhat.seginf.ui.busqueda.BuscarPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.redhat.seginf.ui.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<BuscarPage> getHomePage()
	{
		return BuscarPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
	}
}
