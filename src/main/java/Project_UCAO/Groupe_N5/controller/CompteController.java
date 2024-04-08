package Project_UCAO.Groupe_N5.controller;

import java.time.LocalDate;
import java.util.List;

import Project_UCAO.Groupe_N5.entity.Transaction;
import Project_UCAO.Groupe_N5.repository.CompteRepository;
import Project_UCAO.Groupe_N5.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Project_UCAO.Groupe_N5.entity.Compte;
import Project_UCAO.Groupe_N5.service.CompteService;

@RestController
public class CompteController {
    @Autowired
    private CompteService compteService;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/comptes/{id}/transactions")
    public void effectuerTransaction(@PathVariable Integer id, @RequestParam Double montant, @RequestParam String type) {

        Compte compte = compteRepository.findById(id).get();
        transactionService.effectuerTransaction(compte, montant, type);

    }



    @GetMapping("/comptes")
    public List<Compte> getAll() {
        return compteService.showCompte();
    }

    @GetMapping("/comptes/{id}")
    public Compte get(@PathVariable Integer id) {
        return compteService.getOneCompte(id);
    }

    @PostMapping("/comptes")
    public Compte save(@RequestBody Compte compte) {
        return compteService.saveCompte(compte);
    }

    @PutMapping("/comptes/{id}")
    public Compte update(@PathVariable Integer id, @RequestBody Compte compte) {
        compte.setId(id);
        return compteService.saveCompte(compte);
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Integer id) {
        compteService.deleteCompte(id);
    }


    @GetMapping("/comptes/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Integer id,
                                             @RequestParam(required = false) LocalDate startDate,
                                             @RequestParam(required = false) LocalDate endDate) {

        if (startDate != null && endDate != null) {
            return compteRepository.findByTransactionsDateBetween(startDate, endDate).get(0).getTransactions();
        } else {
            return compteRepository.findById(id).get().getTransactions();
        }
    }
}
