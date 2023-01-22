package com.skypro.cours_3.service;

import com.skypro.cours_3.exception.InSufficientSockQuantity;
import com.skypro.cours_3.exception.InvalidSockRequestException;
import com.skypro.cours_3.model.Color;
import com.skypro.cours_3.dto.SimilarSocks;
import com.skypro.cours_3.model.Size;
import com.skypro.cours_3.model.Socks;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SockServiceImpl implements SockService {
    private final Map<Socks, Integer> socksMap = new HashMap<>();
    @Override
    public Map addSock(SimilarSocks similarSocks) {
        verification(similarSocks);
        Socks socks = mapSocks(similarSocks);
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + similarSocks.getQuantity());
        } else {
            socksMap.put(socks, similarSocks.getQuantity());
        }
        return socksMap;
    }

    @Override
    public void realization(SimilarSocks similarSocks) {
        verification(similarSocks);
        Socks socks = mapSocks(similarSocks);
        int sockQuantity = socksMap.getOrDefault(socks, 0);
        if (sockQuantity >= similarSocks.getQuantity()) {
            socksMap.put(socks, sockQuantity - similarSocks.getQuantity());
        } else {
            throw new InSufficientSockQuantity("Носков нет / There is no socks");
        }
    }

    @Override
    public int getAllQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int counter = 0;
        for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax) {
                continue;
            }
            counter += entry.getValue();
        }
        return counter;
    }
    private Socks mapSocks(SimilarSocks similarSocks) {
        Socks socks = new Socks(similarSocks.getCottonPart(), similarSocks.getSize(), similarSocks.getColor());
        return socks;
    }

    private void verification(SimilarSocks similarSocks) {
        if (similarSocks.getColor() == null || similarSocks.getSize() == null) {
            throw new InvalidSockRequestException("Все поля должны быть заполнены/All fields should be filled");
        }
        if (similarSocks.getQuantity() <= 0) {
            throw new InvalidSockRequestException("Количество должно быть больше 0 / Quantity should be more than 0");
        }
        if (similarSocks.getCottonPart() < 0 || similarSocks.getCottonPart() > 100) {
            throw new InvalidSockRequestException("Процент хлопка должен быть между 0 и 100 / Cotton Percentage should be between 0 and 100");
        }

    }
}
