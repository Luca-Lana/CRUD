package main.dao;

import main.infra.ConnectionFactory;
import main.model.Categoria;
import main.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements IDespesaDAO{

    @Override
    public Despesa insert(Despesa despesa) {
       try(Connection conexao = ConnectionFactory.getConnection()){

           String sql = "INSERT INTO despesas(descricao, valor, data, categoria) VALUES(?, ?, ?, ?)";

           PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,despesa.getDescricao());
            stmt.setDouble(2,despesa.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            stmt.setString(4, despesa.getCategoria().toString());

            stmt.executeUpdate();

            ResultSet resulSet = stmt.getGeneratedKeys();
            resulSet.next();

            Long generateId = resulSet.getLong(1);
            despesa.setId(generateId);

       }catch (SQLException e){
           throw new RuntimeException(e);
       }

        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        try(Connection conexao = ConnectionFactory.getConnection()){

            String sql = "UPDATE despesas SET descricao = ?, valor = ?, data = ?, categoria = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,despesa.getDescricao());
            stmt.setDouble(2,despesa.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            stmt.setString(4, despesa.getCategoria().toString());
            stmt.setLong(5,despesa.getId());

            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return despesa;
    }

    @Override
    public void delete(Long id) {
        try(Connection conexao = ConnectionFactory.getConnection()){

            String sql = "DELETE FROM despesas WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Despesa> findAll() {
        List<Despesa> despesas = new ArrayList<>();
        String sql = " SELECT * FROM despesas";
        try(Connection conexao = ConnectionFactory.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                LocalDate data = rs.getDate("data").toLocalDate();
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                Despesa d = new Despesa(id, descricao, valor, data, categoria);
                despesas.add(d);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return despesas;
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        List<Despesa> despesas = new ArrayList<>();
        String sql = " SELECT * FROM despesas WHERE categoria = ?";
        try(Connection conexao = ConnectionFactory.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.toString());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                LocalDate data = rs.getDate("data").toLocalDate();
                Categoria cate = Categoria.valueOf(rs.getString("categoria"));

                Despesa d = new Despesa(id, descricao, valor, data, cate);
                despesas.add(d);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return despesas;
    }

    @Override
    public Optional<Despesa> findById(Long id) {

        Despesa despesa = null;
        String sql = " SELECT * FROM despesas WHERE id = ?";
        try(Connection conexao = ConnectionFactory.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1,id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long Pkey = rs.getLong("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                LocalDate data = rs.getDate("data").toLocalDate();
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                despesa = new Despesa(Pkey, descricao, valor, data, categoria);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(despesa);
    }
}
