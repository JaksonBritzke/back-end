package com.teste.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalDTO {
    private Long numero;
    private LocalDateTime dataEmissao;
    private Long fornecedorId;
    private BigDecimal valorTotal;
    private List<ItemNotaFiscalDTO> itens;
}