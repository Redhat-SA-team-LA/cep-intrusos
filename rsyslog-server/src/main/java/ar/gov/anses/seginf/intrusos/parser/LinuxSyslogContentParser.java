package ar.gov.anses.seginf.intrusos.parser;

import java.util.HashMap;
import java.util.Map;

import ar.gov.anses.seginf.intrusos.SyslogEvent;
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;

public class LinuxSyslogContentParser extends SyslogContentParser {
	

	private String logReporter;
	private String user;
	private Map<String,String> params=new HashMap<String,String>();

	public LinuxSyslogContentParser(String logMessage) {
		String[] mainOne = logMessage.split(":");
		this.logReporter = mainOne[0].trim();
		this.user = mainOne[1].trim();
		String[] strParam = mainOne[2].split(";");
		for (String param : strParam) {
			String[] keyvalue = param.trim().split("=");
			this.params.put(keyvalue[0].trim(), keyvalue[1].trim());
			System.out.println(param);
		}
		
	}

	public String getLogReporter() {
		return this.logReporter;
	}

	public String getUser() {
		return this.user;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public SyslogEvent parser(SyslogRawMessage syslogRawMessage) {
		
		SyslogEvent syslogEvent = this.buildSyslogEvent(syslogRawMessage);
		
		return null;
		
	}

}
