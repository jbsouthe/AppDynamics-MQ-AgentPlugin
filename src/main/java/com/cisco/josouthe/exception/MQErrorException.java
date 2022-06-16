package com.cisco.josouthe.exception;

public class MQErrorException extends Exception{
    int completionCode, reasonCode;
    String errorType, completionCodeText, reasonCodeText;

    public MQErrorException(String errorType, int completionCode, String completionCodeText, int reasonCode, String reasonCodeText, Throwable mqException) {
        super(mqException);
        this.errorType=errorType;
        this.completionCode=completionCode;
        this.completionCodeText=completionCodeText;
        this.reasonCode=reasonCode;
        this.reasonCodeText=reasonCodeText;
    }

    public int getCompletionCode() { return completionCode; }
    public String getCompletionCodeText() { return completionCodeText; }
    public int getReasonCode() { return reasonCode; }
    public String getReasonCodeText() { return reasonCodeText; }

    public String getMessage() {
        return String.format("%s (Completion Code Text: %s, Reason Code Text: %s)", super.getMessage(), getCompletionCodeText(), getReasonCodeText());
    }
}
