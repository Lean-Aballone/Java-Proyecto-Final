package edu.codoacodo.service;

import edu.codoacodo.domain.Orador;
import edu.codoacodo.infrastucture_persistence.OradorRepository;

public class OradorService {

    private boolean isSQL;
    private OradorRepository repository = new OradorRepository();;
    public void setSQL(boolean SQL) {
        isSQL = SQL;
        repository.setSQL(isSQL);
    }
    public void saveOrador(Orador orador){
        repository.saveOrador(orador);
    }

    public Orador loadOrador(int id){
        return repository.loadOrador(id);
    }

    public void printAllOradorList(){
        repository.printAllOradorList();
    }

    public void deleteOrador(int id){
        repository.deleteOrador(id);
    }

    public void updateOrador(int id) {
        repository.updateOrador(id);
    }
}
