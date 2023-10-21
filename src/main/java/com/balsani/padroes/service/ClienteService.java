package com.balsani.padroes.service;

import com.balsani.padroes.model.Cliente;
import com.balsani.padroes.model.ClienteRepository;
import com.balsani.padroes.model.Endereco;
import com.balsani.padroes.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ClienteService implements ClienteDao {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();

    }

    @Override
    public void inserir(Cliente cliente) {
       String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        buscarPorId(id);
        if (cliente == null) {
            throw new BusinessException("Cliente não cadastrado");
        }
        inserir(cliente);

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }
}
