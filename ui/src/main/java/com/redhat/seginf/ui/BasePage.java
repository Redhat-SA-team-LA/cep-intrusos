package com.redhat.seginf.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.redhat.seginf.ui.busqueda.BuscarPage;
import com.redhat.seginf.ui.complex.ComplexEventPage;
import com.redhat.seginf.ui.evento.NuevoEventoPage;
import com.redhat.seginf.ui.report.ReportPage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage() {

		add(new BookmarkablePageLink<Object>("buscar", BuscarPage.class));
		add(new BookmarkablePageLink<Object>("newEvent", NuevoEventoPage.class));
		add(new BookmarkablePageLink<Object>("report", ReportPage.class));
		add(new BookmarkablePageLink<Object>("complexEvent",
				ComplexEventPage.class));
	}
}
