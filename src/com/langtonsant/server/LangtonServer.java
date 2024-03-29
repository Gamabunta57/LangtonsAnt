package com.langtonsant.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a wrapper of a HttpServer.
 * It launch a server and handle the request.
 * If a valid request is received an event is called in order to launch the main program.
 */
public final class LangtonServer {

    private static final String PUT_METHOD = "PUT";

    private int port;
    private HttpHandler HandleExchange;
    private final List<IRequestListener> requestListeners = new ArrayList<>();

    /**
     * Constructor
     *
     * @param port the port number to listen to (must be between 1 and 65535)
     */
    public LangtonServer(int port) {
        if (port < 1 || port > 65535) throw new IllegalArgumentException("The port must be set between 0 and 65535");

        this.port = port;
        HandleExchange = httpExchange -> {
            int iteration = parseIterationFromHttpExchange(httpExchange);
            if (!isExchangeValid(httpExchange, iteration)) {
                System.out.println("Bad request received");
                sendFailResponse(httpExchange);
                return;
            }

            System.out.println("Request received : " + iteration);
            runApplication(iteration);
            sendSuccessResponse(httpExchange);
        };
    }

    /**
     * Parse the parameter "n" to get the number of iteration
     *
     * @param httpExchange the HTTP Exchange to parse the request from
     * @return the "n" parameter of the request or 0 if it's not valid
     */
    private static int parseIterationFromHttpExchange(HttpExchange httpExchange) {
        String[] parameters = httpExchange.getRequestURI().getQuery().split("&");
        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");
            int length = keyValuePair.length;
            String key = length > 0 ? keyValuePair[0] : "";
            String value = length > 1 ? keyValuePair[1] : "";

            if (key.equals("n")) {
                return Integer.parseInt(value, 10);
            }
        }
        return 0;
    }

    /**
     * Check the given HttpExchange if it's a valid one. It is valid if it use a PUT HTTP verb
     * @param httpExchange The HttpExchange to check
     * @param iteration the number of iteration set in the HttpExchange
     * @return true if the HttpExchange use a PUT verb and if iteration is greater than 0
     */
    private static boolean isExchangeValid(HttpExchange httpExchange, int iteration) {
        return httpExchange.getRequestMethod().equals(PUT_METHOD) && iteration > 0;
    }

    /**
     * Attach a listener to the "OnValidRequestReceived"
     *
     * @param listener the listener to add
     */
    public void addListener(IRequestListener listener) {
        requestListeners.add(listener);
    }

    /**
     * Starts a HttpServer
     *
     * @throws IOException Throw an IOException if the server can't start
     */
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(HandleExchange);
        server.start();
        System.out.println("Server start on port: " + port);
    }

    /**
     * Trigger the OnValidRequestReceived on each listeners
     *
     * @param iteration the number of iteration to run the Langton's ant program
     */
    private void runApplication(int iteration) {
        for (IRequestListener listener : requestListeners) {
            listener.onValidRequestReceived(iteration);
        }
    }

    /**
     * Send a HTTP response to the client
     *
     * @param httpExchange the HTTP Exchange to send the response from
     * @param code         HTTP status code
     * @param message      Body content of the HTTP response
     * @throws IOException send a IOException if the output stream of the HTTP response can't be used
     */
    private void sendResponse(HttpExchange httpExchange, int code, String message) throws IOException {
        byte[] data = message.getBytes();
        httpExchange.sendResponseHeaders(code, data.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(data);
        os.close();
    }

    /**
     * Send a "Fail" message with a status code 500
     *
     * @param httpExchange  the HTTP Exchange to send the response from
     * @throws IOException send a IOException if the output stream of the HTTP response can't be used
     */
    private void sendFailResponse(HttpExchange httpExchange) throws IOException {
        sendResponse(httpExchange, 500, "Fail");
    }

    /**
     * Send a "Success" message with a status code 200
     *
     * @param httpExchange  the HTTP Exchange to send the response from
     * @throws IOException send a IOException if the output stream of the HTTP response can't be used
     */
    private void sendSuccessResponse(HttpExchange httpExchange) throws IOException {
        sendResponse(httpExchange, 200, "Success");
    }
}
