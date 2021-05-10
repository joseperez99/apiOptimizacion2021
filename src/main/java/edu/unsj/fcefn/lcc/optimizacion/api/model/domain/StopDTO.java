package edu.unsj.fcefn.lcc.optimizacion.api.model.domain;

public class StopDTO {

    private Integer id;
    private String name;
    private String city;
    private String province;
    private String country;
    private Integer ranking;

    public void setId(Integer id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCity(String city) { this.city = city; }

    public void setProvince(String province) { this.province = province; }

    public void setCountry(String country) { this.country = country; }

    public void setRanking(Integer ranking) { this.ranking = ranking; }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getCity() { return city; }

    public String getProvince() { return province; }

    public String getCountry() { return country; }

    public Integer getRanking() { return ranking; }

}
