package org.graf.web.formbeans;

/**
 * Created by User on 20.04.2017.
 */
public class Result {

    public static final Result SUCCESS = new Result();

    private final boolean success;
    private final String error;

    public Result(String error) {
        this.error = error;
        this.success = false;
    }

    public Result() {
        this.error = null;
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", error='" + error + '\'' +
                '}';
    }
}
