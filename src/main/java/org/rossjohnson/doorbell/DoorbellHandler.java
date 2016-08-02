package org.rossjohnson.doorbell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.rossjohnson.doorbell.audio.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
class DoorbellHandler implements HttpHandler {
    private DoorbellRinger ringer;

    public DoorbellHandler() {
        ringer = new DoorbellRinger();
    }

    public void handle(HttpExchange http) throws IOException {
        Doorbell.log("Request from " + http.getRemoteAddress() + " - " + http.getRequestURI());
        sendResponse(http);
        ringer.ringDoorbell();
    }

    private void sendResponse(HttpExchange http) throws IOException {
        String response = "Someone rang the doorbell at " + new Date();
        OutputStream os = null;
        try {
            http.sendResponseHeaders(200, response.length());
            os = http.getResponseBody();
            os.write(response.getBytes());
        } finally {
            if (os != null) os.close();
        }
    }
}