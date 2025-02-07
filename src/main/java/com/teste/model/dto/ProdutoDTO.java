package com.teste.model.dto;

import com.teste.model.enums.SituacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long codigo;
    private String descricao;
    private SituacaoProduto situacao;
}