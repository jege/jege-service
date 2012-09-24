package org.jege.util.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.jege.util.JsonMapper;
import org.jege.util.api.MessageApi.Channel;
import org.jege.util.api.MessageApi.Severity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RequestScoped
public class MessagesApi {
    private List<MessageApi> messages = new ArrayList<MessageApi>();
    
    public List<MessageApi> get() {
        return messages;
    }
    
    public String getJson() throws JsonGenerationException, JsonMappingException, IOException {
        return JsonMapper.objectToJson(get());
    }
    
    
    public List<MessageApi> get(MessageApi.Severity severity) {
        if(severity == null) {
            return get();
        } else {
            List<MessageApi> result = new ArrayList<MessageApi>();
            for(MessageApi message : messages) {
                if(severity.equals(message.getSeverity())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    public List<MessageApi> get(String channel) {
        if(channel == null) {
            return get();
        } else {
            List<MessageApi> result = new ArrayList<MessageApi>();
            for(MessageApi message : messages) {
                if(channel.equals(message.getChannel())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    public List<MessageApi> get(MessageApi.Severity severity, String channel) {
        if(severity == null) {
            return get(channel);
        } else if(channel == null) {
            return get(severity);
        } else {
            List<MessageApi> result = new ArrayList<MessageApi>();
            for(MessageApi message : messages) {
                if(severity.equals(message.getSeverity())
                        && channel.equals(message.getChannel())) {
                    result.add(message);
                }
            }
            return result;
        }
    }
    
    // GENERIC
    public void add(MessageApi message) {
        messages.add(message);
    }
    
    public void add(String summary) {
        add(new MessageApi(summary));
    }
    
    public void add(String summary, String detail) {
        add(new MessageApi(summary, detail));
    }
    
    public void add(String summary, Channel channel) {
        add(new MessageApi(summary, channel));
    }
    
    public void add(String summary, Severity severity) {
        add(new MessageApi(summary, severity));
    }
    
    public void add(String summary, String detail, Severity severity) {
        add(new MessageApi(summary, detail, severity));
    }
    
    public void add(String summary, String detail, Channel channel) {
        add(new MessageApi(summary, detail, channel));
    }
    
    public void add(String summary, Severity severity, Channel channel) {
        add(new MessageApi(summary, severity, channel));
    }
    
    public void add(String summary, String detail, Severity severity, Channel channel) {
        add(new MessageApi(summary, detail, severity, channel));
    }
    
    // DEBUG
    public void debug(String summary) {
        add(summary, Channel.DEBUG);
    }
    
    public void debug(String summary, String detail) {
        add(summary, detail, Channel.DEBUG);
    }
    
    // ERROR
    public void error(String summary) {
        add(summary, Severity.ERROR);
    }
    
    public void error(String summary, String detail) {
        add(summary, detail, Severity.ERROR);
    }
    
    public void error(String summary, Channel channel) {
        add(summary, Severity.ERROR, channel);
    }
    
    public void error(String summary, String detail, Channel channel) {
        add(summary, detail, Severity.ERROR, channel);
    }
    
    // WARN
    public void warn(String summary) {
        add(summary, Severity.WARNING);
    }
    
    public void warn(String summary, String detail) {
        add(summary, detail, Severity.WARNING);
    }
    
    public void warn(String summary, Channel channel) {
        add(summary, Severity.WARNING, channel);
    }
    
    public void warn(String summary, String detail, Channel channel) {
        add(summary, detail, Severity.WARNING, channel);
    }
    
    // SUCCESS
    public void success(String summary) {
        add(summary, Severity.SUCCESS);
    }
    
    public void success(String summary, String detail) {
        add(summary, detail, Severity.SUCCESS);
    }
    
    public void success(String summary, Channel channel) {
        add(summary, Severity.SUCCESS, channel);
    }
    
    public void success(String summary, String detail, Channel channel) {
        add(summary, detail, Severity.SUCCESS, channel);
    }
    
    // INFO
    public void info(String summary) {
        add(summary, Severity.INFO);
    }
    
    public void info(String summary, String detail) {
        add(summary, detail, Severity.INFO);
    }
    
    public void info(String summary, Channel channel) {
        add(summary, Severity.INFO, channel);
    }
    
    public void info(String summary, String detail, Channel channel) {
        add(summary, detail, Severity.INFO, channel);
    }
}