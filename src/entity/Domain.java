package entity;

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
    
    
    
    
}
