package bgu.spl.net.api;

import bgu.spl.net.impl.stomp.frames.Frame;
import bgu.spl.net.srv.Connections;

public interface StompMessagingProtocol extends MessagingProtocol<Frame> {
	/**
	 * Used to initiate the current client protocol with its personal connection ID and the connections implementation
	**/
    void start(int connectionId, Connections<Frame> connections);

    void process(Frame message);
	/**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
}
