package ma.projet.classes;

import java.io.Serializable;
import java.util.Objects;

public class EmployeTacheId implements Serializable {

    private int employe;
    private int tache;

    public EmployeTacheId() {
    }

    public EmployeTacheId(int employe, int tache) {
        this.employe = employe;
        this.tache = tache;
    }


    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getTache() {
        return tache;
    }

    public void setTache(int tache) {
        this.tache = tache;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeTacheId that = (EmployeTacheId) o;
        return employe == that.employe && tache == that.tache;
    }

    //Redéfinition des méthodes
    @Override
    public int hashCode() {
        return Objects.hash(employe, tache);
    }
}
