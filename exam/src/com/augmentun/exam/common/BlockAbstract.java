package com.augmentun.exam.common;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

public abstract class BlockAbstract {
    private final Logger logger = Logger.getLogger(BlockAbstract.class);
    //The JSP template of this block.
    public String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String displayBlock(PageContext pageContext) {
        execute(pageContext);
        //Include JSP template.
        Writer body = new StringWriter();
        try {
            if (template != null && !template.trim().equals("")) {
                pageContext.pushBody(body);
                pageContext.include(template);
                pageContext.popBody();
                return body.toString();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                body.close();
             } catch (IOException e) {

             }
        }

        return "";
    }

    abstract protected void execute(PageContext pageContext);
}
