package com.ftn.sbnz.service.simulator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ftn.sbnz.model.drools.DumpsiteDetectionEvent;
import com.ftn.sbnz.model.entity.Landfill;
import com.ftn.sbnz.service.repository.LandfillRepository;
import com.ftn.sbnz.service.service.DumpsiteRiskService;

@Component
public class DumpsiteDetectionSimulator {

    private final DumpsiteRiskService dumpsiteRiskService;
    private final LandfillRepository landfillRepository;

     public DumpsiteDetectionSimulator(DumpsiteRiskService dumpsiteRiskService, LandfillRepository landfillRepository) {
        this.dumpsiteRiskService = dumpsiteRiskService;
        this.landfillRepository = landfillRepository;
    }

    // one simulated detection a day (simulator.detection-interval in
    // application.properties), so visitors aren't flooded with notifications;
    // the first one comes a couple of minutes after startup
    @Scheduled(
        fixedRateString = "${simulator.detection-interval:PT24H}",
        initialDelayString = "${simulator.initial-delay:PT2M}"
    )
    public void simulateDetection() {

        // one random landfill, read on its own instead of loading all of them
        long count = landfillRepository.count();
        if (count == 0) return;

        int index = (int) ThreadLocalRandom.current().nextLong(count);
        Landfill random = landfillRepository.findAll(PageRequest.of(index, 1))
            .getContent()
            .get(0);

        List<String> regions = List.of(
            "Novi Sad",
            "Beograd",
            "Nis",
            "Kragujevac",
            "Subotica"
        );

        String region = regions.get(
            ThreadLocalRandom.current().nextInt(regions.size())
        );

        boolean nearRiver = ThreadLocalRandom.current().nextDouble() < 0.35; // 35%
        boolean nearCity  = ThreadLocalRandom.current().nextDouble() < 0.45; // 45%

        DumpsiteDetectionEvent event = new DumpsiteDetectionEvent(
            String.valueOf(random.getId()),
            region,
            random.getCenterLat(),
            random.getCenterLon(),
            nearRiver,
            nearCity
        );

        dumpsiteRiskService.processDetectionEvent(event);

        System.out.println(
            "SIM EVENT -> id=" + random.getId()
            + ", region=" + region
            + ", river=" + nearRiver
            + ", city=" + nearCity
        );
    }
}
