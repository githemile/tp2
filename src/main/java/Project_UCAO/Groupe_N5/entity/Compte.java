package Project_UCAO.Groupe_N5.entity;



import java.time.LocalDate;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int   numeroCompte;
    private String type;
    private LocalDate dateCreation;
    private double solde;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    // Liste des transactions
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;


}
