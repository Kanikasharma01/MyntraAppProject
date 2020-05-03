package com.upgrad.myntra.service.business;





import com.upgrad.myntra.service.entity.AddressEntity;
import com.upgrad.myntra.service.entity.CustomerAddressEntity;
import com.upgrad.myntra.service.entity.CustomerEntity;
import com.upgrad.myntra.service.entity.StateEntity;
import com.upgrad.myntra.service.exception.AddressNotFoundException;
import com.upgrad.myntra.service.exception.AuthorizationFailedException;
import com.upgrad.myntra.service.exception.SaveAddressException;

import java.util.List;

/*
 * This AddressService interface gives the list of all the service that exist in the address service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface AddressService {

    AddressEntity saveAddress(AddressEntity addressEntity, CustomerAddressEntity customerAddressEntity) throws
            SaveAddressException;
    AddressEntity getAddressByUUID(String addressId, CustomerEntity customerEntity) throws AuthorizationFailedException,
            AddressNotFoundException;
    AddressEntity deleteAddress(AddressEntity addressEntity);
    List<AddressEntity> getAllAddress(CustomerEntity customer);
    StateEntity getStateByUUID(String uuid) throws AddressNotFoundException;
}
