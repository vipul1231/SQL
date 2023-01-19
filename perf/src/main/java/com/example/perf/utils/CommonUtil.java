package com.example.perf.utils;

import com.example.perf.dto.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CommonUtil {


    private static MessageSource msgResource = BeanUtil.getBean(MessageSource.class);


    public static void setMsgResource(MessageSource msgResource) {
        CommonUtil.msgResource = BeanUtil.getBean(MessageSource.class);
    }

    public static ErrorMessage createErrorMessage(String errorCode, String errroMessageKey) {
        return createErrorMessage(errorCode, errroMessageKey, null, null);

    }

    public static ErrorMessage createErrorMessage(String errorCode, String str, Object[] messageparam) {

        return createErrorMessage(errorCode, str, messageparam, null);
    }

    public static ErrorMessage createErrorMessage(String errorCode, String str, String detailErrorMessage) {

        return createErrorMessage(errorCode, str, null, detailErrorMessage);
    }

    public static ErrorMessage createErrorMessage(String errorCode, String str, Object[] messageparam, String detailErrorMessage) {

        ErrorMessage errorMessage = new ErrorMessage(errorCode,
                msgResource.getMessage(str, messageparam, LocaleContextHolder.getLocale()), detailErrorMessage);
        return errorMessage;
    }

    public static LocalDate DateToLocalDate(Date date) {
        LocalDate localDate = null;
        if (null != date) {
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            localDate = localDateTime.toLocalDate();
        }
        return localDate;
    }

    /*
     * public static Date LocalDateToDate(LocalDate localDate) { Instant instant =
     * localDate.toInstant(); return
     * instant.atZone(ZoneId.systemDefault()).toLocalDate(); }
     */
}
