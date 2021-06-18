package luca.dao;

import luca.model.Categoria;
import luca.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface IDespesaDAO {

    Despesa insert(Despesa despesa);
    Despesa update(Despesa despesa);
    void delete(Long id);
    List<Despesa> findAll();
    List<Despesa> findByCateforia(Categoria categoria);
    Optional<Despesa> findById(Long id);

}
