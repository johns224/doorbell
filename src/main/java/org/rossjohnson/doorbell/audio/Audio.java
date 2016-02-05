package org.rossjohnson.doorbell.audio;

import java.io.IOException;

public interface Audio {

	/**
	 * @return What percent of max volume is currently set
	 */
	int getVolume() throws IOException;
	
	void setVolume(int percentOfMax) throws IOException;
}
