package com.balsani.padroes.service;

import com.balsani.padroes.model.Cliente;

public interface ClienteDao {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
