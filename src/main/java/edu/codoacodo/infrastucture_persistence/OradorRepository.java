package edu.codoacodo.infrastucture_persistence;
import edu.codoacodo.Main;
import edu.codoacodo.domain.Orador;

public class OradorRepository {

    private boolean isSQL;
    private IPersistenceOrador persistence;
    public void setSQL(boolean isSQL) {
        this.isSQL = isSQL;
        setPersistence();
    }

    private void setPersistence(){
        if(!isSQL){
            persistence = new edu.codoacodo.infrastucture_persistence.memory.PersistenceOradorImpl();
        }else{
            persistence = new edu.codoacodo.infrastucture_persistence.mysql.PersistenceOradorImpl();
        }
    }

    public void saveOrador(Orador orador){

        persistence.saveOrador(orador);
    }

    public Orador loadOrador(int id){

        return persistence.getOradorById(id);
    }

    public void printAllOradorList(){
        persistence.printAllOradorList();
    }

    public void deleteOrador(int id) {
        persistence.deleteOrador(id);
    }

    public void updateOrador(int id) {
        persistence.updateOrador(id);
    }
}
