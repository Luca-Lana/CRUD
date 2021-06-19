package main.dao;

import main.model.Categoria;
import main.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface IDespesaDAO {

    Despesa insert(Despesa despesa);
    Despesa update(Despesa despesa);
    void delete(Long id);
    List<Despesa> findAll();
    List<Despesa> findByCategoria(Categoria categoria);
    Optional<Despesa> findById(Long id);

}
