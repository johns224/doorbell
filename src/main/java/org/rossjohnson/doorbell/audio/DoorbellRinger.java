package org.rossjohnson.doorbell.audio;

import org.rossjohnson.doorbell.Doorbell;

/**
 * Created by ross on 8/2/16.
 */
public class DoorbellRinger {

    private Audio audio;

    public DoorbellRinger() {
        audio = AudioFactory.getAudio();
    }

    public void ringDoorbell() {
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
