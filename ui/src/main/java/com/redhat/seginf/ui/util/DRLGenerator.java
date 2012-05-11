package com.redhat.seginf.ui.util;

public class DRLGenerator {

	// por ahora lo voy a definir aca, despues lo quitare...

	private String drl = "";

	private String packageAndImports = "package org.drools.examples.twittercbr\n"
			+ "import ar.gov.anses.seginf.intrusos.convert.* \n"
			+ "import ar.gov.anses.seginf.intrusos.notification.* \n"
			+ "import java.text.MessageFormat \n";

	private String sysLogMessageDeclaration = "declare SyslogMessage \n "
			+ "	@role( event ) \n" + "	@timestamp( createdAt ) \n "
			+ "	@expires( 10s ) \n " + "end\n";

	public DRLGenerator() {
	}

	public String getDRL() {
		return this.drl;
	}

	public DRLGenerator buildImports() {
		this.drl += this.getImports();
		return this;
	}

	public DRLGenerator buildDeclarations() {
		this.drl += this.getDeclarations();
		return this;
	}

	public RuleGenerator buildRule() {
		return new RuleGenerator(this);
	}

	public void addRule(String rule) {
		this.drl += rule;
	}

	public String build() {
		return this.drl;
	}

	// ********************PRIVATE************************

	private String getDeclarations() {
		return this.sysLogMessageDeclaration;
	}

	private String getImports() {
		return this.packageAndImports;
	}

}
