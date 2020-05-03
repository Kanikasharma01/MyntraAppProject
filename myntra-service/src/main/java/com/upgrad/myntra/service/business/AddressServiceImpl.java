package com.upgrad.myntra.service.business;



import com.upgrad.myntra.service.dao.AddressDao;
import com.upgrad.myntra.service.entity.AddressEntity;
import com.upgrad.myntra.service.entity.CustomerAddressEntity;
import com.upgrad.myntra.service.entity.CustomerEntity;
import com.upgrad.myntra.service.entity.StateEntity;
import com.upgrad.myntra.service.exception.AddressNotFoundException;
import com.upgrad.myntra.service.exception.AuthorizationFailedException;
import com.upgrad.myntra.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service public class AddressServiceImpl implements AddressService {

	@Autowired private AddressDao addressDao;

	/**
	 * The method implements the business logic for save address endpoint.
	 */
	@Override @Transactional(propagation = Propagation.REQUIRED) public AddressEntity saveAddress(
			AddressEntity addressEntity, CustomerAddressEntity customerAddressEntity) throws SaveAddressException {
	}

	/**
	 * The method implements the business logic for get address by uuid endpoint.
	 */
	@Override public AddressEntity getAddressByUUID(String addressId, CustomerEntity customerEntity)
			throws AuthorizationFailedException, AddressNotFoundException {
	}


	/**
	 * The method implements the business logic for saving customer address.
	 */
	@Transactional(propagation = Propagation.REQUIRED) public CustomerAddressEntity saveCustomerAddress(
			CustomerAddressEntity customerAddressEntity) {
	}

	/**
	 * The method implements the business logic for delete address endpoint.
	 */
	@Override @Transactional(propagation = Propagation.REQUIRED) public AddressEntity deleteAddress(
			AddressEntity addressEntity) {
	}

	/**
	 * The method implements the business logic for getting all saved address endpoint.
	 */
	@Override public List<AddressEntity> getAllAddress(CustomerEntity customer) {
	}

	/**
	 * The method implements the business logic for getting state by id.
	 */
	@Override public StateEntity getStateByUUID(String uuid) throws AddressNotFoundException {
	}


}
