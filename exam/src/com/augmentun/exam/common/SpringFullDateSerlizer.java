package com.augmentun.exam.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.augmentun.exam.util.StringUtil;

public class SpringFullDateSerlizer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = jsonParser.getText();
        if (!StringUtil.validateParam(dateString)){
            return null;
        }
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return date;
    }

}
