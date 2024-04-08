package Project_UCAO.Groupe_N5.service;

import Project_UCAO.Groupe_N5.entity.Compte;
import Project_UCAO.Groupe_N5.entity.Transaction;
import Project_UCAO.Groupe_N5.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private CompteRepository compteRepository;

    public void effectuerTransaction(Compte compte, Double montant, String type) {

        // Vérifier si le compte a des fonds suffisants
        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }

        // Créer une nouvelle transaction
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setMontant(montant);
        transaction.setType(type);
        transaction.setCompte(compte);

        // Mettre à jour le solde du compte
        compte.setSolde(compte.getSolde() - montant);

        // Enregistrer la transaction et le compte mis à jour
        compteRepository.save(compte);
    }



}