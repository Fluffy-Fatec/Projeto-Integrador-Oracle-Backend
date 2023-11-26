package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import com.fluffy.backend.entity.NotificationStock;
import com.fluffy.backend.service.NotificationStockService;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationStockController {

    @Autowired
    private NotificationStockService notificationStockService;

    @PostMapping
    public ResponseEntity<NotificationStock> salvarOuAtualizarNotificationStock(@RequestBody NotificationStock notificationStock) {
        NotificationStock savedNotificationStock = notificationStockService.salvarOuAtualizarNotificationStock(notificationStock);
        return new ResponseEntity<>(savedNotificationStock, HttpStatus.CREATED);
    }

    @GetMapping("/{nsId}")
    public ResponseEntity<NotificationStock> buscarNotificationStockPorId(@PathVariable Long nsId) {
        return notificationStockService.buscarNotificationStockPorId(nsId)
                .map(notificationStock -> new ResponseEntity<>(notificationStock, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<NotificationStock>> buscarTodasNotificationStocks() {
        List<NotificationStock> notificationStocks = notificationStockService.buscarTodasNotificationStocks();
        return new ResponseEntity<>(notificationStocks, HttpStatus.OK);
    }

    @PutMapping("/{nsId}/updateStatus")
    public ResponseEntity<NotificationStock> updateStatus(
            @PathVariable Long nsId,
            @RequestBody Map<String, String> requestBody
    ) {
        try {
            String nsStatus = requestBody.get("nsStatus");
            if (nsStatus == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            NotificationStock updatedNotificationStock = notificationStockService.atualizarStatusNotificationStock(nsId, nsStatus);
            return new ResponseEntity<>(updatedNotificationStock, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
