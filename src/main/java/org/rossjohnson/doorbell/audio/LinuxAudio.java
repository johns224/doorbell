package org.rossjohnson.doorbell.audio;

import java.io.IOException;

/**
 * Created by ross on 8/2/16.
 */
public class LinuxAudio implements Audio {

    private static final String[] PLAY_DOORBELL_CMD = {"/usr/bin/aplay", "/storage/downloads/doorbell.wav"};


    public int getVolume() throws IOException {
        return 0;
    }

    public void setVolume(int percentOfMax) throws IOException {

    }

    public void playClip() throws Exception {
        Runtime.getRuntime().exec(PLAY_DOORBELL_CMD);

    }
}
