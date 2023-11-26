package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fluffy.backend.entity.NotificationStock;
import com.fluffy.backend.repository.NotificationStockRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NotificationStockServiceTest {

    @Mock
    private NotificationStockRepository notificationStockRepository;

    @InjectMocks
    private NotificationStockService notificationStockService;

    @Test
    void testSalvarOuAtualizarNotificationStock() {
        NotificationStock notificationStock = new NotificationStock();
        when(notificationStockRepository.saveAndFlush(notificationStock)).thenReturn(notificationStock);

        NotificationStock result = notificationStockService.salvarOuAtualizarNotificationStock(notificationStock);

        assertNotNull(result);
        verify(notificationStockRepository, times(1)).saveAndFlush(notificationStock);
    }

    @Test
    void testBuscarNotificationStockPorId() {
        Long nsId = 1L;
        NotificationStock notificationStock = new NotificationStock();
        when(notificationStockRepository.findById(nsId)).thenReturn(Optional.of(notificationStock));

        Optional<NotificationStock> result = notificationStockService.buscarNotificationStockPorId(nsId);

        assertTrue(result.isPresent());
        assertEquals(notificationStock, result.get());
        verify(notificationStockRepository, times(1)).findById(nsId);
    }

    @Test
    void testBuscarTodasNotificationStocks() {
        List<NotificationStock> notificationStocks = new ArrayList<>();
        when(notificationStockRepository.findAll()).thenReturn(notificationStocks);

        List<NotificationStock> result = notificationStockService.buscarTodasNotificationStocks();

        assertNotNull(result);
        assertEquals(notificationStocks, result);
        verify(notificationStockRepository, times(1)).findAll();
    }

    @Test
    void testAtualizarStatusNotificationStock() throws NotFoundException {
        Long nsId = 1L;
        String nsStatus = "NEW";
        NotificationStock notificationStock = new NotificationStock();
        when(notificationStockRepository.findById(nsId)).thenReturn(Optional.of(notificationStock));
        when(notificationStockRepository.save(notificationStock)).thenReturn(notificationStock);

        NotificationStock result = notificationStockService.atualizarStatusNotificationStock(nsId, nsStatus);

        assertNotNull(result);
        assertEquals(nsStatus, result.getNsStatus());
        verify(notificationStockRepository, times(1)).findById(nsId);
        verify(notificationStockRepository, times(1)).save(notificationStock);
    }

    @Test
    void testAtualizarStatusNotificationStockNotFound() {
        Long nsId = 1L;
        String nsStatus = "NEW";
        when(notificationStockRepository.findById(nsId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            notificationStockService.atualizarStatusNotificationStock(nsId, nsStatus);
        });

        verify(notificationStockRepository, times(1)).findById(nsId);
        verify(notificationStockRepository, never()).save(any());
    }
}
