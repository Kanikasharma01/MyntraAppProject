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

public class AddressController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    /**
     * A controller method to save an address in the database.
     *
     * @body SaveAddressRequest - This argument contains all the attributes required to store address details in the database.
     * @param authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<SaveAddressResponse> type object along with Http status CREATED.
     * @throws AuthorizationFailedException
     * @throws SaveAddressException
     * @throws AddressNotFoundException
     */

    /**
     * A controller method to delete an address from the database.
     *
     * @param addressId    - The uuid of the address to be deleted from the database.
     * @param authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<DeleteAddressResponse> type object along with Http status OK.
     * @throws AuthorizationFailedException
     * @throws AddressNotFoundException
     */

    /**
     * A controller method to get all address from the database.
     *
     * @param authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<AddressListResponse> type object along with Http status OK.
     * @throws AuthorizationFailedException
     */

}
