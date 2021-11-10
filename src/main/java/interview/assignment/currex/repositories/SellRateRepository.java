package interview.assignment.currex.repositories;

import interview.assignment.currex.entities.SellRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellRateRepository extends JpaRepository<SellRate, Long> {

    @Query("select s from SellRate s where s.currency = ?1 and s.date = ?2 and s.dataTable = ?3")
    List<SellRate> findAllSellRateByCurrencyAndDateAndTable(String currency, String date, String table);
}
