package com.fluffy.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.NotificationStockDTO;
import com.fluffy.backend.entity.NotificationStock;
import com.fluffy.backend.repository.NotificationStockRepository;

@Service
@Transactional
public class NotificationStockService {

    @Autowired
    private NotificationStockRepository notificationStockRepository;

    public NotificationStock salvarOuAtualizarNotificationStock(NotificationStock notificationStock) {
        return notificationStockRepository.saveAndFlush(notificationStock);
    }

    public Optional<NotificationStock> buscarNotificationStockPorId(Long nsId) {
        return notificationStockRepository.findById(nsId);
    }

    public List<NotificationStock> buscarTodasNotificationStocks() {
        return notificationStockRepository.findAll();
    }

    public NotificationStock atualizarStatusNotificationStock(Long nsId, String nsStatus) throws NotFoundException {
        NotificationStock notificationStock = notificationStockRepository.findById(nsId)
                .orElseThrow(NotFoundException::new);

        notificationStock.setNsStatus(nsStatus);
        return notificationStockRepository.save(notificationStock);
    }
}