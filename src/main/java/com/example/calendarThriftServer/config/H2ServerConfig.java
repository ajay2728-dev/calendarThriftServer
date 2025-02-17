//package com.example.calendarThriftServer.config;
//
//import org.h2.tools.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.annotation.PostConstruct;
//import java.sql.SQLException;
//
//@Configuration
//public class H2ServerConfig {
//
//    private Server h2Server;
//
//    @PostConstruct
//    public void startServer() throws SQLException {
//        // Start the H2 server as soon as the Spring context is initialized
//        h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091").start();
//        System.out.println("H2 TCP server started on port 9092...");
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server h2ServerBean() throws SQLException {
//        return h2Server;
//    }
//}
