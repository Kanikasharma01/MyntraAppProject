package com.upgrad.myntra.api.controllers;



import com.upgrad.myntra.service.business.AddressService;
import com.upgrad.myntra.service.business.CustomerService;
import com.upgrad.myntra.service.entity.AddressEntity;
import com.upgrad.myntra.service.entity.CustomerAddressEntity;
import com.upgrad.myntra.service.entity.CustomerEntity;
import com.upgrad.myntra.service.exception.AddressNotFoundException;
import com.upgrad.myntra.service.exception.AuthorizationFailedException;
import com.upgrad.myntra.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upgrad.myntra.api.model.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/address")
public class AddressController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    /**
     * A controller method to save an address in the database.
     *
     * @body SaveAddressRequest - This argument contains all the attributes required to store address details in the database.
     * @param //authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<SaveAddressResponse> type object along with Http status CREATED.
     * @throws //AuthorizationFailedException
     * @throws //SaveAddressException
     * @throws //AddressNotFoundException
     */
    @PostMapping("/")
    public ResponseEntity<SaveAddressResponse>saveAddress(SaveAddressRequest saveAddressRequest,@RequestHeader("authorization")final String authentication) throws Exception{
        CustomerEntity customerEntity = customerService.getCustomer(authentication);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(saveAddressRequest.getCity());
        addressEntity.setUuid(saveAddressRequest.getStateUuid());
        addressEntity.setFlatBuilNo(saveAddressRequest.getFlatBuildingName());
        addressEntity.setLocality(saveAddressRequest.getLocality());
        addressEntity.setPincode(saveAddressRequest.getPincode());

        CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
        customerAddressEntity.setId(customerEntity.getId());
        customerAddressEntity.setCustomer(customerEntity);
        customerAddressEntity.setAddress(addressEntity);

        addressEntity = addressService.saveAddress(addressEntity,customerAddressEntity);
        SaveAddressResponse aur=new SaveAddressResponse().id(addressEntity.getUuid()).status("ADDRESS SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SaveAddressResponse>(aur, HttpStatus.OK);
    }

    /**
     * A controller method to delete an address from the database.
     *
     * @param //addressId    - The uuid of the address to be deleted from the database.
     * @param //authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<DeleteAddressResponse> type object along with Http status OK.
     * @throws //AuthorizationFailedException
     * @throws //AddressNotFoundException
     */
    @DeleteMapping("/{addressId}")
    public ResponseEntity<DeleteAddressResponse>deleteAddress(@PathVariable("addressId") String addressId,@RequestHeader("authorization")final String authentication)throws Exception{
        CustomerEntity customerEntity=customerService.getCustomer(authentication);
        if(addressId!=customerEntity.getUuid())
            throw new AuthorizationFailedException("ATHR-004","You are not authorized to view/update/delete any one else's address");
        addressService.deleteAddress(addressService.getAddressByUUID(addressId,customerEntity));
        DeleteAddressResponse deleteAddressResponse=new DeleteAddressResponse().id(UUID.fromString(addressId));
        return new ResponseEntity<DeleteAddressResponse>(deleteAddressResponse,HttpStatus.OK);
    }

    /**
     * A controller method to get all address from the database.
     *
     * @param //authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<AddressListResponse> type object along with Http status OK.
     * @throws //AuthorizationFailedException
     */
    @GetMapping("/customer")
    public ResponseEntity<AddressListResponse>getAllAddress(@RequestHeader("authorization")final String authentication)throws Exception{
        CustomerEntity customerEntity=customerService.getCustomer(authentication);
        List<AddressEntity>list=addressService.getAllAddress(customerEntity);
        List<AddressList>addressLists1=null;
        for(int i=0;i<list.size();i++){
            AddressListState addressListState=new AddressListState().id(UUID.fromString(list.get(i).getState().getUuid())).stateName(list.get(i).getState().getStateName());
            AddressList addressList=new AddressList().id(UUID.fromString(list.get(i).getUuid())).flatBuildingName(list.get(i).getFlatBuilNo()).city(list.get(i).getCity()).locality(list.get(i).getLocality()).pincode(list.get(i).getPincode()).state(addressListState);
            addressLists1.add(addressList);
        }

        final AddressListResponse addressLists = new AddressListResponse().addresses(addressLists1);
        return new ResponseEntity<AddressListResponse>(addressLists, HttpStatus.OK);
    }

}
