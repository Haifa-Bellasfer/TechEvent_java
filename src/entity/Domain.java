package entity;

import java.util.Objects;

/**
 *
 * @author Ihèb
 */
public class Domain {
    private int idDomain;
    private String nameDomain;

    public Domain() {
    }

    public Domain(int idDomaine, String nameDomain) {
        this.idDomain = idDomaine;
        this.nameDomain = nameDomain;
    }

    public int getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }

    public String getNameDomain() {
        return nameDomain;
    }

    public void setNameDomain(String nameDomain) {
        this.nameDomain = nameDomain;
    }

    @Override
    public String toString() {
        return nameDomain;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domain other = (Domain) obj;
        if (!Objects.equals(this.nameDomain, other.nameDomain)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
