package ar.gov.anses.seginf.intrusos;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import ar.gov.anses.seginf.intrusos.convert.Rfc3164SyslogConverter;
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;
import ar.gov.anses.seginf.intrusos.parser.LinuxSyslogContentParser;

public class RSyslogServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger
			.getLogger(RSyslogServerHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	private WorkingMemoryEntryPoint cepEntryPoint;

	public RSyslogServerHandler(WorkingMemoryEntryPoint ep) {
		this.cepEntryPoint = ep;
	}

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		byte[] bytes = ((ChannelBuffer) e.getMessage()).array();

		SyslogRawMessage syslogRawMessage = Rfc3164SyslogConverter
				.parseMessage(bytes);

		LinuxSyslogContentParser parser = new LinuxSyslogContentParser(
				syslogRawMessage.getLogMessage());


		//esto va todo junto, es un objeto que recibe el CEP para procesarlo.
		//hay que envolverlo para poder
		SyslogEvent event = new SyslogEvent();
		event.setCreatedAt(new Date());
		event.setUser(parser.getUser());

		System.out.println(event.toString());
		
		this.cepEntryPoint.insert(event);

		System.out.println(syslogRawMessage);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
}