package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.AddressRequest;
import org.example.onlineshopbackend.api.output.AddressInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.model.entity.Address;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.repo.AddressRepo;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;
    private final UserRepo userRepo;

    public MessageResponse createAddress(AddressRequest addressRequest) {
         addressRepo.save(initAddress(addressRequest,true));
         return new MessageResponse("Address created");
    }

    private Address initAddress(AddressRequest addressRequest, boolean isCreated) {
        Address address = addressRequest.getAddressInfo();
        User user = userRepo.findById(addressRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        address.setUser(user);
        if (isCreated) {
            user.addAddress(address);
        }
        return address;
    }

    public List<AddressInfo> findAddressByUserId(Long userId) {
        return addressRepo.findByUserId(userId).stream().map(AddressInfo::getAddressInfo).toList();
    }

    public MessageResponse updateAddress(AddressRequest addressRequest) {
        addressRepo.save(initAddress(addressRequest,false));
        return new MessageResponse("Address updated");
    }

    public MessageResponse deleteAddress(Long addressId) {
        Address address = addressRepo.findById(addressId).orElseThrow(() -> new UsernameNotFoundException("Address not found!"));
        addressRepo.delete(address);
        return new MessageResponse("Address deleted");
    }
}
