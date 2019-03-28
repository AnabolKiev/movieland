package com.anabol.movieland.web.utils;

import java.beans.PropertyEditorSupport;

public class CurrencyConverter extends PropertyEditorSupport {

    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(Currency.getByName(text));
    }
}
