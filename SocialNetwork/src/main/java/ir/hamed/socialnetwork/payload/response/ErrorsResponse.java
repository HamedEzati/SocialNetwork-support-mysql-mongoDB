package ir.hamed.socialnetwork.payload.response;

import java.util.List;

public class ErrorsResponse {
    private List<String> errors;

    public ErrorsResponse(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
