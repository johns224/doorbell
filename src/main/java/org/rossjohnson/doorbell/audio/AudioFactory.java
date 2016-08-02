package org.rossjohnson.doorbell.audio;

import org.rossjohnson.doorbell.OSType;

/**
 * Created by ross on 8/2/16.
 */
public class AudioFactory {
    public static Audio getAudio() {
        if (OSType.isMac()) {
            return new MacAudio();
        }
        else if (OSType.isLinux()) {
            return new LinuxAudio();
        }
        return new NoopAudio();
    }
}
