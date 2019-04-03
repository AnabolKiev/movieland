package com.anabol.movieland.web.auth.interceptor;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class AuthInterceptorTest {

    @Test
    public void testPreHandleDummy() throws Exception {
        AuthInterceptor authInterceptor = new AuthInterceptor();
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        HttpServletResponse httpServletResponseMock = new MockHttpServletResponse();
        assertFalse(authInterceptor.preHandle(httpServletRequestMock, httpServletResponseMock, null));
        assertEquals(HttpServletResponse.SC_FORBIDDEN, httpServletResponseMock.getStatus());
    }

}