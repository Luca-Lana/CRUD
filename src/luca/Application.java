package luca;

import luca.dao.DespesaDAO;
import luca.model.Categoria;
import luca.model.Despesa;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {


        DespesaDAO dao = new DespesaDAO();

        Despesa despesa = new Despesa("Pagamento do Uber", Categoria.TRANSPORTE, 300.00, LocalDate.of(2021,5, 10));

        dao.insert(despesa);
    }
}
