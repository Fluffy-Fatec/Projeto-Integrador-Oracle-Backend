package com.fluffy.backend.controller;

import com.fluffy.backend.entity.NotificationStock;
import com.fluffy.backend.service.NotificationStockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class NotificationStockControllerTest {

    @Mock
    private NotificationStockService notificationStockService;

    @InjectMocks
    private NotificationStockController notificationStockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarOuAtualizarNotificationStock() {
        NotificationStock notificationStock = new NotificationStock();
        when(notificationStockService.salvarOuAtualizarNotificationStock(any())).thenReturn(notificationStock);

        ResponseEntity<NotificationStock> response = notificationStockController.salvarOuAtualizarNotificationStock(notificationStock);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(notificationStock, response.getBody());
    }

    @Test
    void testBuscarNotificationStockPorIdFound() {
        NotificationStock notificationStock = new NotificationStock();
        when(notificationStockService.buscarNotificationStockPorId(anyLong())).thenReturn(Optional.of(notificationStock));

        ResponseEntity<NotificationStock> response = notificationStockController.buscarNotificationStockPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificationStock, response.getBody());
    }

    @Test
    void testBuscarNotificationStockPorIdNotFound() {
        when(notificationStockService.buscarNotificationStockPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<NotificationStock> response = notificationStockController.buscarNotificationStockPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testBuscarTodasNotificationStocks() {
        List<NotificationStock> notificationStocks = Collections.singletonList(new NotificationStock());
        when(notificationStockService.buscarTodasNotificationStocks()).thenReturn(notificationStocks);

        ResponseEntity<List<NotificationStock>> response = notificationStockController.buscarTodasNotificationStocks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificationStocks, response.getBody());
    }

    @Test
    void testUpdateStatus() throws NotFoundException {
        NotificationStock updatedNotificationStock = new NotificationStock();
        when(notificationStockService.atualizarStatusNotificationStock(anyLong(), anyString())).thenReturn(updatedNotificationStock);

        Map<String, String> requestBody = Collections.singletonMap("nsStatus", "NEW");
        ResponseEntity<NotificationStock> response = notificationStockController.updateStatus(1L, requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedNotificationStock, response.getBody());
    }

    @Test
    void testUpdateStatusNotFound() throws NotFoundException {
        when(notificationStockService.atualizarStatusNotificationStock(anyLong(), anyString())).thenThrow(NotFoundException.class);

        Map<String, String> requestBody = Collections.singletonMap("nsStatus", "NEW");
        ResponseEntity<NotificationStock> response = notificationStockController.updateStatus(1L, requestBody);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
