package org.rossjohnson.doorbell;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Doorbell {
	private static final int DEFAULT_PORT = 9000;
	private HttpServer server;

	public static void main(String[] args) throws Exception {
		int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);
		Doorbell doorbell = new Doorbell(port);
		log("Starting doorbell server on port " + port);
		doorbell.startServer();
	}

	public Doorbell(int port) throws Exception {
		System.setProperty("java.net.preferIPv4Stack", "true");
		server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/doorbell", new DoorbellHandler());
		server.setExecutor(null);
	}

	public void startServer() throws IOException {
		server.start();
	}

	public static void log(String log) {
		System.out.println("[" + new Date() + "] " + log);
	}

}