package com.twilioresponse.service;

import com.twilioresponse.model.CallModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CallService {

    public String makeCall(CallModel callModel);

    public void initiateVoice(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void getUserResponse(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
