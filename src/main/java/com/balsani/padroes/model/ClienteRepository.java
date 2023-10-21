package com.balsani.padroes.model;

import com.balsani.padroes.model.Cliente;
import org.springframework.data.repository.CrudRepository;


public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
