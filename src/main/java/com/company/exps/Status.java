package com.company.exps;

public enum Status {
    NOT_FOUND(404, "Not Found"),
    ALREADY_HAVE(250,"Already Have "),
    WRONG_DATA(303,"Something went wrong" ),
    SUCCESSFUL(200, "Alright");
    private int code;
    private String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
