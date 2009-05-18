package errorsloger;

import java.util.Date;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class MCVLoggedError {

    private Exception exception;
    private Date date;
    private String message;
    private Integer id;
    private String source;

    public MCVLoggedError(Integer id, Exception exception, Date date, String message, String source) {
        this.id = id;
        this.exception = exception;
        this.date = date;
        this.message = message;
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public Exception getException() {
        return exception;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }
}
