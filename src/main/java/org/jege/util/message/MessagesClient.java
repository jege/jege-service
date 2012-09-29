package org.jege.util.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.jege.util.JsonMapper;
import org.jege.util.message.MessageClient.Type;
import org.jege.util.message.MessageClient.Severity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RequestScoped
public class MessagesClient {
    private List<MessageClient> messages = new ArrayList<MessageClient>();
    
    public List<MessageClient> get() {
        return messages;
    }
    
    public String getJson() throws JsonGenerationException, JsonMappingException, IOException {
        return JsonMapper.objectToJson(get());
    }
    
    
    public List<MessageClient> get(MessageClient.Severity severity) {
        if(severity == null) {
            return get();
        } else {
            List<MessageClient> result = new ArrayList<MessageClient>();
            for(MessageClient message : messages) {
                if(severity.equals(message.getSeverity())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    public List<MessageClient> get(String channel) {
        if(channel == null) {
            return get();
        } else {
            List<MessageClient> result = new ArrayList<MessageClient>();
            for(MessageClient message : messages) {
                if(channel.equals(message.getType())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    public List<MessageClient> get(MessageClient.Severity severity, String channel) {
        if(severity == null) {
            return get(channel);
        } else if(channel == null) {
            return get(severity);
        } else {
            List<MessageClient> result = new ArrayList<MessageClient>();
            for(MessageClient message : messages) {
                if(severity.equals(message.getSeverity())
                        && channel.equals(message.getType())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    // GENERIC
    public void add(MessageClient message) {
        messages.add(message);
    }
    
    public void add(String summary) {
        add(new MessageClient(summary));
    }
    
    public void add(String summary, String detail) {
        add(new MessageClient(summary, detail));
    }
    
    public void add(String summary, Type channel) {
        add(new MessageClient(summary, channel));
    }
    
    public void add(String summary, Severity severity) {
        add(new MessageClient(summary, severity));
    }
    
    public void add(String summary, String detail, Severity severity) {
        add(new MessageClient(summary, detail, severity));
    }
    
    public void add(String summary, String detail, Type channel) {
        add(new MessageClient(summary, detail, channel));
    }
    
    public void add(String summary, Severity severity, Type channel) {
        add(new MessageClient(summary, severity, channel));
    }
    
    public void add(String summary, String detail, Severity severity, Type channel) {
        add(new MessageClient(summary, detail, severity, channel));
    }
    
    // DEBUG
    public void debug(String summary) {
        add(summary, Type.DEBUG);
    }
    
    public void debug(String summary, String detail) {
        add(summary, detail, Type.DEBUG);
    }
    
    // ERROR
    public void error(String summary) {
        add(summary, Severity.ERROR);
    }
    
    public void error(String summary, String detail) {
        add(summary, detail, Severity.ERROR);
    }
    
    public void error(String summary, Type channel) {
        add(summary, Severity.ERROR, channel);
    }
    
    public void error(String summary, String detail, Type channel) {
        add(summary, detail, Severity.ERROR, channel);
    }
    
    // WARN
    public void warn(String summary) {
        add(summary, Severity.WARNING);
    }
    
    public void warn(String summary, String detail) {
        add(summary, detail, Severity.WARNING);
    }
    
    public void warn(String summary, Type channel) {
        add(summary, Severity.WARNING, channel);
    }
    
    public void warn(String summary, String detail, Type channel) {
        add(summary, detail, Severity.WARNING, channel);
    }
    
    // SUCCESS
    public void success(String summary) {
        add(summary, Severity.SUCCESS);
    }
    
    public void success(String summary, String detail) {
        add(summary, detail, Severity.SUCCESS);
    }
    
    public void success(String summary, Type channel) {
        add(summary, Severity.SUCCESS, channel);
    }
    
    public void success(String summary, String detail, Type channel) {
        add(summary, detail, Severity.SUCCESS, channel);
    }
    
    // INFO
    public void info(String summary) {
        add(summary, Severity.INFO);
    }
    
    public void info(String summary, String detail) {
        add(summary, detail, Severity.INFO);
    }
    
    public void info(String summary, Type channel) {
        add(summary, Severity.INFO, channel);
    }
    
    public void info(String summary, String detail, Type channel) {
        add(summary, detail, Severity.INFO, channel);
    }
}