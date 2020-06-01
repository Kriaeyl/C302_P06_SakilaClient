package sg.edu.rp.dmsd.c302_p06_sakilaclient;

public class Store {
    private String address, city, country;

    public Store(String address, String city, String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
