package com.langtonsant.server;

public interface IRequestListener {

    void OnGoodRequestReceived(int iteration);
     * Event send when a valid request has been received
     *
     * @param iteration the number of iteration to run the Langton's ant
     */
}
