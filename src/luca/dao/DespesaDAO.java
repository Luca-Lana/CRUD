package luca.dao;

import luca.infra.ConnectionFactory;
import luca.model.Categoria;
import luca.model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements IDespesaDAO{

    @Override
    public Despesa insert(Despesa despesa) {
       try(Connection conexao = ConnectionFactory.getConnection()){

           String sql = "INSERT INTO despesas(descricao, valor, data, categoria) VALUES(?, ?, ?, ?)";

           PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,despesa.getDescricao());
            stmt.setDouble(2,despesa.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            stmt.setString(4, despesa.getCategoria().toString());

            stmt.executeUpdate();

       }catch (SQLException e){
           throw new RuntimeException(e);
       }

        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Despesa> findAll() {
        return null;
    }

    @Override
    public List<Despesa> findByCateforia(Categoria categoria) {
        return null;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        return Optional.empty();
    }
}
