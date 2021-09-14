package com.twilioresponse.twimlresponse;

import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Redirect;
import com.twilio.twiml.voice.Say;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TwimlResponseController {

    @GetMapping(path = "/")
    public String welcome() {
        return "Hello World";
    }

    private AtomicInteger callerNumber = new AtomicInteger();

    @PostMapping(path = "/call", produces = "application/xml")
    @ResponseBody
    public String respondToPhoneCall() {

        VoiceResponse.Builder voiceBuilder = new VoiceResponse.Builder();
        Say greeting = new Say.Builder("Hello Caller this is working").voice(Say.Voice.ALICE).build();
        Play music = new Play.Builder("https://demo.twilio.com/docs/classic.mp3").build();
        return voiceBuilder.say(greeting).play(music).build().toXml();

    }

    @PostMapping("/gather")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TwiML twiml = new VoiceResponse.Builder()
                .gather(
                        new Gather.Builder()
                                .numDigits(1)
                                .say(new Say.Builder("For sales, press 1. For support, press 2.").build())
                                .build()
                )
                .redirect(new Redirect.Builder("/voice").build())
                .build();

        response.setContentType("application/xml");
        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendGather(VoiceResponse.Builder builder) {
        builder.gather(new Gather.Builder()
                .numDigits(1)
                .say(new Say.Builder("For sales, press 1. For support, press 2.").build())
                .build()
        )
                .redirect(new Redirect.Builder("/voice").build());
    }

}
