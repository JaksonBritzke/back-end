package com.teste.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teste.model.enums.SituacaoProduto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue
    private Long codigo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoProduto situacao = SituacaoProduto.ATIVO;

    @JsonManagedReference
    @OneToMany(mappedBy = "produto")
    private List<ItemNotaFiscal> itens;
}