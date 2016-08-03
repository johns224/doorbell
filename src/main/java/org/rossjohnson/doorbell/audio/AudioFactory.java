package org.rossjohnson.doorbell.audio;

import org.rossjohnson.doorbell.Doorbell;
import org.rossjohnson.doorbell.OSType;

/**
 * Created by ross on 8/2/16.
 */
public class AudioFactory {
    public static Audio getAudio() {
        Audio audio;
        if (OSType.isMac()) {
            audio = new MacAudio();
        }
        else if (OSType.isLinux()) {
            audio = new LinuxAudio();
        }
        else {
            audio = new NoopAudio();
        }
        Doorbell.log("Using audio of type " + audio.getClass().getSimpleName());
        return audio;
    }
}
