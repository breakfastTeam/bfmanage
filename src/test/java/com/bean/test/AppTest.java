package com.bean.test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class AppTest {
    public static void main(String[] args) {
        Server jettyServer = new Server(8080);

        WebAppContext wah = new WebAppContext();
        wah.setContextPath("/bfmanage");
        wah.setWar("src/main/webapp");
        jettyServer.setHandler(wah);
        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
