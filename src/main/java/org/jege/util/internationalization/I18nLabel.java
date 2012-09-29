package org.jege.util.internationalization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;

import org.jege.util.JsonMapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class I18nLabel {
    public static final String TYPE = "org.jege.util.internationalization.I18nUserType";
    
    private Map<Locale, String> map = new HashMap<Locale, String>();

    public String getValue() {
        return getValue(getCurrentLocale());
    }

    public String getValue(Locale locale) {
        return map.get(locale);
    }

    public void setValue(String value) {
        setValue(getCurrentLocale(), value);
    }

    public void setValue(Locale locale, String value) {
        map.put(locale, value);
    }

    private Locale getCurrentLocale() {
        ExpressionFactory expressionFactory = ExpressionFactory.newInstance();
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        String expression = "#{selectedLocale}";
        Object result = expressionFactory.createValueExpression(context, expression, Locale.class).getValue(context);

        if (result != null) {
            return Locale.class.cast(result);
        } else {
            return null;
        }
    }

    public String serialize() {
//        StringBuffer buffer = new StringBuffer();
//        for (Entry<Locale, String> entry : map.entrySet()) {
//            String localeStr = entry.getKey().toString();
//            buffer.append("[");
//            buffer.append(localeStr);
//            buffer.append("]");
//            buffer.append(entry.getValue());
//            buffer.append("[/");
//            buffer.append(localeStr);
//            buffer.append("]");
//        }
//        return buffer.toString();
        try {
            return JsonMapper.objectToJson(map);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void explode(String serializedValue) {
//        if (map == null) {
//            map = new HashMap<Locale, String>();
//        }
//
//        String regex = "\\[[a-z]{2}_[A-Z]{2}\\].*?\\[/[a-z]{2}_[A-Z]{2}\\]";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(serializedValue);
//
//        while (m.find()) {
//            Locale locale = new Locale(m.group().substring(1, 3), m.group().substring(4, 6));
//            map.put(locale, m.group().substring(7, m.group().length() - 8));
//        }
        try {
            Map<String, Object> deserializedMap = JsonMapper.jsonToMap(serializedValue);
            
            for(Entry<String, Object> entry : deserializedMap.entrySet()) {
                map.put(new Locale(entry.getKey()), entry.getValue().toString());
            }
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
