package com.teste.model.dto;

import java.time.LocalDate;

import com.teste.model.enums.SituacaoFornecedor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long codigo;
    private String razaoSocial;
    private String email;
    private String endereco;
    private String telefone;
    private String cnpj;
    private SituacaoFornecedor situacao;
    private LocalDate dataBaixa;
}
