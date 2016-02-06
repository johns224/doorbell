package org.rossjohnson.doorbell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.rossjohnson.doorbell.audio.MacAudio;
import org.rossjohnson.doorbell.audio.NoopAudio;
import org.rossjohnson.doorbell.audio.Audio;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
class DoorbellHandler implements HttpHandler {
	private Audio audio;

	public DoorbellHandler() {
		audio = createAudio();
	}

	private Audio createAudio() {
		if (OSType.isMac()) {
			return new MacAudio();
		}
		return new NoopAudio();
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
			playClip();
			Thread.sleep(2000);
			audio.setVolume(origVol);

		} catch (Exception e) {
			Doorbell.log("Error playing doorbell sound ");
			e.printStackTrace();
		}
	}

	private void playClip() throws Exception {
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/doorbell.wav")));
		clip.start();
	}
}