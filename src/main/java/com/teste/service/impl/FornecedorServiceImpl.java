package com.teste.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.teste.model.Fornecedor;
import com.teste.model.dto.FornecedorDTO;
import com.teste.repository.FornecedorRepository;
import com.teste.service.FornecedorService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {
    @Inject
    FornecedorRepository repository;

    @Override
    public List<FornecedorDTO> listarTodos() {
        return repository.listAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FornecedorDTO buscarPorId(Long id) {
        return toDTO(repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado")));
    }

    @Transactional
    @Override
    public FornecedorDTO salvar(FornecedorDTO dto) {
        validarCamposUnicos(dto);
        Fornecedor fornecedor = toFornecedor(dto);
        repository.persist(fornecedor);
        return toDTO(fornecedor);
    }

    @Transactional
    @Override
    public FornecedorDTO atualizar(FornecedorDTO dto) {
        Fornecedor existente = repository.findByIdOptional(dto.getCodigo())
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
        existente.setCnpj(dto.getCnpj());
        existente.setDataBaixa(dto.getDataBaixa());
        existente.setEmail(dto.getEmail());
        existente.setRazaoSocial(dto.getRazaoSocial());
        existente.setEndereco(dto.getEndereco());
        existente.setTelefone(dto.getTelefone());
        existente.setSituacao(dto.getSituacao());
        return toDTO(existente);
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        if (repository.temMovimentacao(id)) {
            throw new BadRequestException(
                    "Este Fornecedor já possui vinculo com uma Nota Fiscal, portanto não poderá ser excluído");
        }
        repository.deleteById(id);
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        return new FornecedorDTO(
                fornecedor.getCodigo(),
                fornecedor.getRazaoSocial(),
                fornecedor.getEmail(),
                fornecedor.getEndereco(),
                fornecedor.getTelefone(),
                fornecedor.getCnpj(),
                fornecedor.getSituacao(),
                fornecedor.getDataBaixa());
    }

    private Fornecedor toFornecedor(FornecedorDTO dto) {
        return new Fornecedor(
                dto.getCodigo(),
                dto.getRazaoSocial(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEndereco(),
                dto.getCnpj(),
                dto.getSituacao(),
                dto.getDataBaixa());
    }

    @Override
    public FornecedorDTO buscarPorDescricao(String descricao) {
        return toDTO(repository.findByDescricao(descricao)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado")));
    }

    @Override
    public List<FornecedorDTO> buscarPorDescricaoLike(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        List<Fornecedor> fornecedores = repository.findByRazaoSocialLike(razaoSocial);
        if (fornecedores.isEmpty()) {

            throw new NotFoundException("Nenhum fornecedor encontrado com a Razão Social fornecida");
        }

        return fornecedores.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private void validarCamposUnicos(FornecedorDTO dto) {
        if (repository.count("cnpj", dto.getCnpj()) > 0) {
            throw new BadRequestException("CNPJ já cadastrado");
        }
    }
}