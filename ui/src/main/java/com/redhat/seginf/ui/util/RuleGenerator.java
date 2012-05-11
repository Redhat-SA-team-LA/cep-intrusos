package com.redhat.seginf.ui.util;

public class RuleGenerator {

	private DRLGenerator generator;

	private String rule = "rule >>ruleName<< \n" + "no-loop true \n "
			+ "when \n" + "	$s : SyslogMessage( \n"
			+ "		    severity.toString matches >>severity<< , \n"
			+ "		    facility.toString matches >>facility<< , \n"
			+ "		    hostname.toString matches >>hostname<< , \n"
			+ "		    logMessage.toString matches >>logMessage<<\n"
			+ "	    ) from entry-point \"syslog\" \n" + "then \n"
			+ "		new Curl().invoke( >>message<< ); \n" + "end\n";

	public RuleGenerator(DRLGenerator generator) {
		this.generator = generator;
	}

	public RuleGenerator buildProperty(String property, String value) {
		this.rule = this.rule.replaceAll(">>" + property + "<<",
				this.comillas() + value + this.comillas());
		return this;
	}

	private String comillas() {
		return "\"";
	}

	public DRLGenerator end() {
		this.rule = this.rule.replaceAll(">>.*<<", "\".*\""); // todo lo que no
																// se
																// habia podido
		// reemplazar le pone una rexep
		// generica
		this.generator.addRule(this.rule);
		return this.generator;
	}

}
