package org.rossjohnson.doorbell.audio;

import java.io.IOException;

public class NoopAudio implements Audio {

	public int getVolume() throws IOException {
		return 0;
	}

	public void setVolume(int percentOfMax) throws IOException {
	}

}
