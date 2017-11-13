package ua.shtain.irina.newbacking.data.exeptions;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class ConnectionLostException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Internet connection lost";
    }
}
