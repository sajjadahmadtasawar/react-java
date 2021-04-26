package no.ssb.sivadmin.payload.response

class MessageResponse {
    private String message

    MessageResponse(String message) {
        this.message = message
    }

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }
}