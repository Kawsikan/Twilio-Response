package com.twilioresponse.controller;

import com.twilioresponse.model.CallModel;
import com.twilioresponse.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String welcome() {
        return "Welcome !";
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