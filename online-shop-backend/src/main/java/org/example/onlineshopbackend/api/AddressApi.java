package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.AddressRequest;
import org.example.onlineshopbackend.api.input.CartItemInfo;
import org.example.onlineshopbackend.api.output.AddressInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.model.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressApi {

    private final AddressService addressService;


    @PostMapping("/")
    public ResponseEntity<MessageResponse> createAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressRequest));
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> updateAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(addressRequest));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<MessageResponse> deleteAddress(@PathVariable("addressId") long addressId) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAddress(addressId));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<AddressInfo>> getAddressByUserId(@PathVariable("userId") long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAddressByUserId(userId));
    }

}
