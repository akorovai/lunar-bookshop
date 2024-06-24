package dev.s24377.lunar_bookshop.client.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduldedTasks {


    private final ClientTypeSwitchingService clientTypeSwitchingService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void checkAndSwitchClientTypes() {
        clientTypeSwitchingService.checkAndSwitchClientTypes();
    }
}
