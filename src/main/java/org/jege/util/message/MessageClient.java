package org.jege.util.message;

public class MessageClient {
    public static final Severity DEFAULT_SEVERITY = Severity.INFO;
    public static final Type DEFAULT_CHANNEL = Type.GLOBAL;
    
    private String summary;
    private String detail;
    private Severity severity = DEFAULT_SEVERITY;
    private Type channel = DEFAULT_CHANNEL;
    
    public MessageClient() {
        super();
    }
    
    public MessageClient(String summary) {
        this();
        this.summary = summary;
    }
    
    public MessageClient(String summary, String detail) {
        this(summary);
        this.detail = detail;
    }
    
    public MessageClient(String summary, Type channel) {
        this(summary);
        this.channel = channel;
    }
    
    public MessageClient(String summary, Severity severity) {
        this(summary);
        this.severity = severity;
    }
    
    public MessageClient(String summary, String detail, Severity severity) {
        this(summary, severity);
        this.detail= detail;
    }
    
    public MessageClient(String summary, String detail, Type channel) {
        this(summary, detail);
        this.channel= channel;
    }
    
    public MessageClient(String summary, Severity severity, Type channel) {
        this(summary);
        this.channel= channel;
        this.severity = severity;
    }
    
    public MessageClient(String summary, String detail, Severity severity, Type channel) {
        this(summary, detail, severity);
        this.channel= channel;
    }
    
    public enum Severity {
        ERROR,
        WARNING,
        SUCCESS,
        INFO;
    }
    
    public enum Type {
        GLOBAL,
        NOTIFY,
        DEBUG,
        VALIDATION;
    }

    public Type getType() {
        return channel;
    }

    public void setChannel(Type channel) {
        this.channel = channel;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
