package com.redhat.seginf.ui;

import org.junit.Test;

import com.google.gson.GsonBuilder;
import com.redhat.seginf.domain.SyslogMessage;
import com.redhat.seginf.ui.repository.Repository;
import com.redhat.seginf.ui.util.DRLGenerator;

public class DataTest {

	@Test
	public void testGSon() {
		SyslogMessage message = new SyslogMessage();
		message.setId(1L);

		System.out.println(new GsonBuilder().create().toJson(message));

	}

	@Test
	public void testGenerator() {

		System.out.println(
				new DRLGenerator()
					.buildImports()
					.buildDeclarations()
					.buildRule()
						.buildProperty("severity", "MAJOR")
						.end()
					.build());

	}

	@Test
	public void list() {
		System.out.println(Repository.getInstance().loadAll().size());
	}

}
