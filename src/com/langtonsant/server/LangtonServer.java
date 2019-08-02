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

public class LangtonServer {

    private static final String PUT_METHOD = "PUT";

    private int port;
    private HttpHandler HandleExchange;
    private List<IRequestListener> requestListeners = new ArrayList<>();

    public LangtonServer(int port){
        this.port = port;
        this.HandleExchange = httpExchange -> {
            int iteration = parseIterationFromHttpExchange(httpExchange);
            if(!isExchangeValid(httpExchange, iteration)){
                System.out.println("Bad request received");
                sendFailResponse(httpExchange);
                return;
            };

            System.out.println("Request received : " + iteration);
            runApplication(iteration);
            sendSuccessResponse(httpExchange);
        };
    }

    public void addListener(IRequestListener listener){
        requestListeners.add(listener);
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port),0);
        HttpContext context = server.createContext("/");
        context.setHandler(HandleExchange);
        server.start();
        System.out.println("Server start");
    }

    private void runApplication(int iteration){
        for(IRequestListener listener : requestListeners)
            listener.OnGoodRequestReceived(iteration);
    }

    private void sendResponse(HttpExchange httpExchange, int code, String message) throws IOException {
        byte[] data = message.getBytes();
        httpExchange.sendResponseHeaders(500, data.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(data);
        os.close();
    }

    private void sendFailResponse(HttpExchange httpExchange) throws IOException {
       sendResponse(httpExchange, 500,"Fail");
    }

    private void sendSuccessResponse(HttpExchange httpExchange) throws IOException {
        sendResponse(httpExchange, 200,"Success");
    }

    private static int parseIterationFromHttpExchange(HttpExchange httpExchange){
        String[] parameters = httpExchange.getRequestURI().getQuery().split("&");
        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");
            int length = keyValuePair.length;
            String key = length > 0 ? keyValuePair[0] : "";
            String value = length > 1 ? keyValuePair[1] : "";

            if (key.equals("n"))
                return Integer.parseInt(value, 10);
        }
        return 0;
    }

    private static boolean isExchangeValid(HttpExchange httpExchange, int iteration) {
        return httpExchange.getRequestMethod().equals(PUT_METHOD) && iteration > 0;
    }
}
