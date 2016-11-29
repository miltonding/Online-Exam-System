package com.augmentun.exam.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TestTaglib extends TagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspTagException {
        return SKIP_BODY;
    }

    @Override
    //execute from < to >
    public int doEndTag() throws JspTagException {
        JspWriter out = pageContext.getOut();
        try {
            out.println("bbbbbbrwqrqwrqwrqrtrt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();

    }
}
