package ar.gov.anses.seginf.intrusos.convert;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SyslogMessage extends BusinessObject implements Serializable{

//	CREATE TABLE syslogmessage (
//		    id bigint NOT NULL,
//		    createdat timestamp without time zone,
//		    executed boolean NOT NULL,
//		    facility character varying(255),
//		    hostname character varying(255),
//		    localaddress character varying(255),
//		    logmessage character varying(2048),
//		    remoteaddress character varying(255),
//		    severity character varying(255),
//		    "timestamp" timestamp without time zone,
//		    valid boolean NOT NULL
//		);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -185301188423679119L;

	@Column(nullable=true,length=255)
	private String facility;
	
	@Column(nullable=true,length=255)
	private String severity;
	
	@Column(nullable=true,length=255)
	private String remoteAddress;
	
	@Column(nullable=true,length=255)
	private String localAddress;
	
	@Column(nullable=true,length=255)
	private String hostname;
	
	@Column(nullable=true,length=2048)
	private String logMessage;
	
	private Date timestamp;
	private Date createdAt;

	private boolean executed = false;
	private boolean valid = true;

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}



	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "SyslogMessage{<facility>" + facility.toString() + "</facility>"
				+ ", severity=" + severity + ", remoteAddress='"
				+ remoteAddress + "'" + ", localAddress='" + localAddress + "'"
				+ ", hostname='" + hostname + "'" + ", messageTime="
				+ timestamp + "<content>" + this.logMessage.trim()
				+ "</content>" + '}';
	}
}
