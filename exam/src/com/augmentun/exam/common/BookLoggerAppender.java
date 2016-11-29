package com.augmentun.exam.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class BookLoggerAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        // TODO Auto-generated method stub
        return this.getThreshold().equals(priority);
    }
}
