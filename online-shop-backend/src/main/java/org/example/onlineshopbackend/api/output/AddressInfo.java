package org.example.onlineshopbackend.api.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.Address;

@Getter
@Setter
@AllArgsConstructor
public class AddressInfo {

    private Long id;
    private String street;
    private String city;
    private String houseNo;

    public static AddressInfo getAddressInfo(Address address) {
        return new AddressInfo(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getHouseNo()
        );
    }
}
