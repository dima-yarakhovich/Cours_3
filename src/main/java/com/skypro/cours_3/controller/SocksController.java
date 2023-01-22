package com.skypro.cours_3.controller;

import com.skypro.cours_3.exception.InSufficientSockQuantity;
import com.skypro.cours_3.exception.InvalidSockRequestException;
import com.skypro.cours_3.model.Color;
import com.skypro.cours_3.dto.SimilarSocks;
import com.skypro.cours_3.model.Size;
import com.skypro.cours_3.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SocksController {
    private final SockService sockService;

    public SocksController(SockService sockService) {
        this.sockService = sockService;
    }
    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockRequestException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }

    @ExceptionHandler(InSufficientSockQuantity.class)
    public ResponseEntity<String> handleInSufficientException(InSufficientSockQuantity inSufficientSockQuantity) {
        return ResponseEntity.badRequest().body(inSufficientSockQuantity.getMessage());
    }


    @PostMapping
    @Operation(summary = "Приход носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки поставлены на приход"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<?> addSock(@RequestBody SimilarSocks similarSocks) {
        return ResponseEntity.ok(sockService.addSock(similarSocks));
    }

    @PutMapping
    @Operation(summary = "Реализация носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки реализованы"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public void saleSocks(@RequestBody SimilarSocks similarSocks) {
        sockService.realization(similarSocks);
    }

    @GetMapping
    @Operation(summary = "Просмотр наличия на носков на складе согласно указанных параметров")
    public ResponseEntity<Integer> getAllQuantity(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return ResponseEntity.ok(sockService.getAllQuantity(color, size, cottonMin, cottonMax));
    }
    @DeleteMapping
    @Operation(summary = "Списание носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки списаны"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public void removeSocks(@RequestBody SimilarSocks similarSocks) {
        sockService.realization(similarSocks);
    }

}
