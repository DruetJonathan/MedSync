package com.jdbk.medsync.service;

import com.jdbk.medsync.model.entity.Produit;

import java.util.List;

public interface ProduitService {

    // Ajouter un nouveau produit
    public Long addProduit(Produit produit);

    // Mettre à jour les informations d'un produit existant
    public Produit updateProduit(long id, Produit produit);

    // Supprimer un produit par son identifiant
    public Produit removeProduit(Long produitId);

    // Obtenir un produit par son identifiant
    public Produit getProduitById(Long produitId);

    // Obtenir la liste de tous les produits
    public List<Produit> getAll();

    // Réduire la quantité d'un produit en stock
    public Produit updateStockProduit(Long produitId, Long quantiteReduite);

}
