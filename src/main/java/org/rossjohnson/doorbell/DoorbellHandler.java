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
	private Audio audio;

	public DoorbellHandler() {
		audio = AudioFactory.getAudio();
	}

	public void handle(HttpExchange http) throws IOException {
		Doorbell.log("Request from " + http.getRemoteAddress() + " - " + http.getRequestURI());
		sendResponse(http);
		ringDoorbell();
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

	private void ringDoorbell() {
		try {
			int origVol = audio.getVolume();
			audio.setVolume(80);
			audio.playClip();
			Thread.sleep(2000);
			audio.setVolume(origVol);

		} catch (Exception e) {
			Doorbell.log("Error playing doorbell sound ");
			e.printStackTrace();
		}
	}
}