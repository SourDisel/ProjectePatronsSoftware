package Model;

public class Producte {
    private int codi;
    private String nom;
    private Categoria categoria;
    private double preu;
    private String tipusPreu; // unitari / pes / paquet
    private int stock;
    private boolean oferta;
    private int idCategoria;

    public Producte(int codi, String nom, Categoria categoria, double preu, String tipusPreu, int stock, boolean oferta) {
        this.codi = codi;
        this.nom = nom;
        this.categoria = categoria;
        this.preu = preu;
        this.tipusPreu = tipusPreu;
        this.stock = stock;
        this.oferta = oferta;
    }
    public Producte(String nom, int idCategoria, double preu, String tipusPreu, int stock) {
        this.nom = nom;
        this.idCategoria = idCategoria;
        this.preu = preu;
        this.tipusPreu = tipusPreu;
        this.stock = stock;
    }
    public int getIdCategoria() {
        return getCategoria().getId();
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.length() < 2) {
            throw new IllegalArgumentException("El nom ha de tenir almenys 2 caràcters.");
        }
        this.nom = nom;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        if (preu < 0) {
            throw new IllegalArgumentException("El preu ha de ser positiu.");
        }
        this.preu = Math.round(preu * 100.0) / 100.0;
    }

    public String getTipusPreu() {
        return tipusPreu;
    }

    public void setTipusPreu(String tipusPreu) {
        if (!tipusPreu.equals("unitari") && !tipusPreu.equals("pes") && !tipusPreu.equals("paquet")) {
            throw new IllegalArgumentException("Tipus preu ha de ser 'unitari', 'pes' o 'paquet'.");
        }
        this.tipusPreu = tipusPreu;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock ha de ser 0 o més.");
        }
        this.stock = stock;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "codi=" + codi +
                ", nom='" + nom + '\'' +
                ", categoria=" + (categoria != null ? categoria.toString() : "null") +
                ", preu=" + preu +
                ", tipusPreu='" + tipusPreu + '\'' +
                ", stock=" + stock +
                ", oferta=" + oferta +
                '}';
    }
}
