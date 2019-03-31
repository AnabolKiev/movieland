package com.anabol.movieland.web.auth;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertTrue;

public class LogInterceptorTest {

    @Test
    public void testPreHandleDummy() throws Exception {
        LogInterceptor logInterceptor = new LogInterceptor();
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        HttpServletResponse httpServletResponseMock = new MockHttpServletResponse();
        assertTrue(logInterceptor.preHandle(httpServletRequestMock, httpServletResponseMock, null));
    }



}
