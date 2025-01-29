package org.example.onlineshopbackend.api.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.onlineshopbackend.model.entity.Address;

@Getter
@Setter
@ToString
public class AddressRequest {
    private  Long id;
    private  String street;
    private  String city;
    private  String houseNo;
    private  Long userId ;

    public Address getAddressInfo() {
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setCity(city);
        address.setHouseNo(houseNo);
        return address;
    }
}
