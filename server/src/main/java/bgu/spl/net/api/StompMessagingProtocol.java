package bgu.spl.net.api;

import bgu.spl.net.impl.stomp.frames.Frame;
import bgu.spl.net.srv.Connections;

import java.io.Serializable;

public interface StompMessagingProtocol<T> extends MessagingProtocol<T> {
	/**
	 * Used to initiate the current client protocol with its personal connection ID and the connections implementation
	**/
    void start(int connectionId, Connections<T> connections);

    void process(T message);
	/**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
}
