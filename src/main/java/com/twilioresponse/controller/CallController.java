package com.twilioresponse.controller;

import com.twilioresponse.model.CallModel;
import com.twilioresponse.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("twilio")
public class CallController {

    @Autowired
    private CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping("/call")
    public String makeCallByTwilio(@RequestBody CallModel callModel) {
        String makeCallResponse = callService.makeCall(callModel);
        return makeCallResponse;
    }

    @PostMapping("/initiate-voice")
    public void initiateVoiceByTwilio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        callService.initiateVoice(request, response);

    }

    @PostMapping("/gather")
    public void getUserResponseByTwilio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        callService.getUserResponse(request, response);
    }

}