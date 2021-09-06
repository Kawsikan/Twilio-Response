package com.twilioresponse.twimlresponse;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Say;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TwimlResponseController {

    private AtomicInteger callerNumber = new AtomicInteger();

    @GetMapping(path = "/call", produces = "application/xml")
    @ResponseBody
    public String respondToPhoneCall() {

        VoiceResponse.Builder voiceBuilder = new VoiceResponse.Builder();
        Say greeting = new Say.Builder("Hello Caller this is working.").voice(Say.Voice.ALICE).build();
        Play music = new Play.Builder("http://demo.twilio.com/docs/classic.mp3").build();
        return voiceBuilder.say(greeting).play(music).build().toXml();

    }

    @GetMapping(path = "/")
    public String welcome() {
        return "Hello World";
    }
}
