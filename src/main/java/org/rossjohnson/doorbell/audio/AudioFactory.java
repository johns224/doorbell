package org.rossjohnson.doorbell.audio;

import org.rossjohnson.doorbell.Doorbell;
import org.rossjohnson.doorbell.OSType;

/**
 * Created by ross on 8/2/16.
 */
public class AudioFactory {
    public static Audio getAudio() {
        Audio retVal;
        if (OSType.isMac()) {
            retVal =  new MacAudio();
        }
        else if (OSType.isLinux()) {
            retVal =  new LinuxAudio();
        }
        else {
            retVal = new NoopAudio();
        }
        Doorbell.log("Using audio of type " + retVal.getClass().getSimpleName());
        return retVal;
    }
}
