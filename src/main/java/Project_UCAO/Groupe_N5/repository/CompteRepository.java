package Project_UCAO.Groupe_N5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import Project_UCAO.Groupe_N5.entity.Compte;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface CompteRepository  extends JpaRepository <Compte, Integer>{
    List<Compte> findByTransactionsDateBetween(LocalDate startDate, LocalDate endDate);
    Compte save(Compte compte);
    
}
