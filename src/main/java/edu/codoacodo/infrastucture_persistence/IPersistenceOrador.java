package edu.codoacodo.infrastucture_persistence;

import edu.codoacodo.domain.Orador;

public interface IPersistenceOrador {

    void saveOrador(Orador orador);
    Orador getOradorById(int id);
    void printAllOradorList();
    void deleteOrador(int id);

    void updateOrador(int id);

}
