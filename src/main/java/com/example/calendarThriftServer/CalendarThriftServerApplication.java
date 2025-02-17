package com.example.calendarThriftServer;

import com.example.thriftMeeting.IMeetingService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CalendarThriftServerApplication {

	private static final Logger log = LoggerFactory.getLogger(CalendarThriftServerApplication.class);

	public static void startServer(MeetingHandler meetingHandler) throws TTransportException {
		try{
			TServerTransport serverTransport = new TServerSocket(9090);
			IMeetingService.Processor<IMeetingService.Iface> processor = new IMeetingService.Processor<>(meetingHandler);
			TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
			log.info("server running at port no. 8091");
			log.info("thrift server running at port no. 9090");
			server.serve();
		}catch (TTransportException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws TTransportException {
		ApplicationContext applicationContext= SpringApplication.run(CalendarThriftServerApplication.class, args);
		startServer(applicationContext.getBean(MeetingHandler.class));
	}

}
