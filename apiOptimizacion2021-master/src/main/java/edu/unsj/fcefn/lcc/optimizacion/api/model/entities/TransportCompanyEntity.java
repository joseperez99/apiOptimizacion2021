package edu.unsj.fcefn.lcc.optimizacion.api.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "transport_companies")
public class TransportCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private  String name;

    @Column(name = "logo")
    private  String logo;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
