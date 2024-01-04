package dev.evgeni.peopleapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.evgeni.peopleapi.configuration.DevInstrumationSender;


@RestController
@Profile("dev")
public class InstrumentationSenderController {

    @Autowired
    private DevInstrumationSender sender;


    @GetMapping("/senddevstatistics")
    public String sendStatistics() {
        return sender.sendStatistic();
    }


}
