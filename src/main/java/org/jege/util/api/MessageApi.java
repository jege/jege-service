package org.jege.util.api;

public class MessageApi {
    public static final Severity DEFAULT_SEVERITY = Severity.INFO;
    public static final Channel DEFAULT_CHANNEL = Channel.GLOBAL;
    
    private String summary;
    private String detail;
    private Severity severity = DEFAULT_SEVERITY;
    private Channel channel = DEFAULT_CHANNEL;
    
    public MessageApi() {
        super();
    }
    
    public MessageApi(String summary) {
        this();
        this.summary = summary;
    }
    
    public MessageApi(String summary, String detail) {
        this(summary);
        this.detail = detail;
    }
    
    public MessageApi(String summary, Channel channel) {
        this(summary);
        this.channel = channel;
    }
    
    public MessageApi(String summary, Severity severity) {
        this(summary);
        this.severity = severity;
    }
    
    public MessageApi(String summary, String detail, Severity severity) {
        this(summary, severity);
        this.detail= detail;
    }
    
    public MessageApi(String summary, String detail, Channel channel) {
        this(summary, detail);
        this.channel= channel;
    }
    
    public MessageApi(String summary, Severity severity, Channel channel) {
        this(summary);
        this.channel= channel;
        this.severity = severity;
    }
    
    public MessageApi(String summary, String detail, Severity severity, Channel channel) {
        this(summary, detail, severity);
        this.channel= channel;
    }
    
    public enum Severity {
        ERROR,
        WARNING,
        SUCCESS,
        INFO;
    }
    
    public enum Channel {
        GLOBAL(true),
        NOTIFY(true),
        DEBUG(true),
        MODAL(false),
        FORM(false);
        
        public static final String PREFIX_NON_UNIQUE = "@";
        private boolean unique;
        
        private Channel(boolean unique) {
            this.unique = unique;
        }
        
        public boolean isUnique() {
            return unique;
        }
        
        public String getName() {
            return (isUnique() ? "" : PREFIX_NON_UNIQUE) + toString().toLowerCase();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
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
