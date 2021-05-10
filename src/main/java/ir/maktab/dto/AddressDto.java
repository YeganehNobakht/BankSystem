package ir.maktab.dto;

public class AddressDto {
    private String city;
    private String street;
    private String alley;

    public AddressDto(String city, String street, String alley) {
        this.city = city;
        this.street = street;
        this.alley = alley;
    }

    public AddressDto() {

    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getAlley() {
        return alley;
    }

    public AddressDto setAlley(String alley) {
        this.alley = alley;
        return this;
    }
}
