package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.impl.stomp.frames.ErrorFrame;
import bgu.spl.net.impl.stomp.frames.Frame;
import bgu.spl.net.srv.Connections;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {

    private boolean shouldTerminate = false;
    private int connectionId;
    private Connections<Frame> connections;

    @Override
    public void start(int connectionId, Connections<Frame> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(Frame message) {
        String error = "";
        switch (message.getCommand()) {
            case "CONNECT":
                error = Frame.isConnectFrame(message);
                if (error != null)
                    connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, error)));
//                else
//                    connect;
                break;
            case "SUBSCRIBE":
                error = Frame.isSubscribeFrame(message);
                if (error != null)
                    connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, error)));
//                else
//                    subscribe;
                break;
            case "UNSUBSCRIBE":
                error = Frame.isUnsubscribeFrame(message);
                if (error != null)
                    connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, error)));
//                else
//                    unsubscribe;
                break;
            case "SEND":
                error = Frame.isSendFrame(message);
                if (error != null)
                    connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, error)));
//                else
//                    send;
                break;
            case "DISCONNECT":
                error = Frame.isDisconnectFrame(message);
                if (error != null)
                    connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, error)));
//                else
//                    disconnect;
                break;
            default:
                connections.send(connectionId, new ErrorFrame("receiptId", Frame.errorBody(message, "Invalid command")));
        }
    }

//    private void connect(String version, String host) {
//        if (version.equals("1.2") && host.equals("stomp.cs.bgu.ac.il")) {
//            connections.addClient(connectionId);
//            connections.send(connectionId, "CONNECTED\nversion:1.2\n\n\0");
//        } else {
//            connections.send(connectionId, "ERROR\nmessage:Wrong version or host\n\n\0");
//        }
//    }
//
//    private void subscribe(String destination, String id, String receipt) {
//        if (connections.subscribe(destination, id, connectionId)) {
//            connections.send(connectionId, "RECEIPT\nreceipt-id:" + receipt + "\n\n\0");
//        } else {
//            connections.send(connectionId, "ERROR\nmessage:Already subscribed to " + destination + "\n\n\0");
//        }
//    }
//
//    private void unsubscribe(String id) {
//        if (connections.unsubscribe(id, connectionId)) {
//            connections.send(connectionId, "RECEIPT\nreceipt-id:" + id + "\n\n\0");
//        } else {
//            connections.send(connectionId, "ERROR\nmessage:Not subscribed to " + id + "\n\n\0");
//        }
//    }
//
//    private void send(String destination, String receipt, String[] splitMessage) {
//        String body = "";
//        for (int i = 1; i < splitMessage.length; i++) {
//            body += splitMessage[i] + "\n";
//        }
//    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
