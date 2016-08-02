package org.rossjohnson.doorbell.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAudio implements Audio {

	private static final String GET_VOLUME_CMD[] = {"osascript", "-e", "get volume settings"};
	private static String SET_VOLUME_CMD[] = {"osascript", "-e", "" };
	private static final String VOLUME_FMT = "#.#";
	private static final Pattern VOLUME_REGEX = Pattern.compile("^output volume:(\\d+),.*");
	
	public int getVolume() throws IOException {
		ProcessBuilder pb = new ProcessBuilder(GET_VOLUME_CMD);
		pb.redirectErrorStream(true);
		return parseVolume(getOutput(pb.start()));
	}


	public void setVolume(int percentOfMax) throws IOException {
		SET_VOLUME_CMD[2] = "set volume " + new DecimalFormat(VOLUME_FMT).format(percentOfMax / (100/7)); // valid values are 0-7, go figure... 
		Runtime.getRuntime().exec(SET_VOLUME_CMD);
	}

    public void playClip() throws Exception {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/doorbell.wav")));
        clip.start();
    }

    private int parseVolume(String output) {
		Matcher m = VOLUME_REGEX.matcher(output);
		if (m.matches()) {
			return Integer.parseInt(m.group(1));
		}
		return -1;
	}

	private String getOutput(Process p) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = in.readLine();
		try { p.waitFor(); } catch (InterruptedException e) {} 
		in.close();
		return line;
	}

}
