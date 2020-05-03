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
import java.util.regex.Pattern;

@Service public class AddressServiceImpl implements AddressService {

	@Autowired private AddressDao addressDao;


	/**
	 * The method implements the business logic for save address endpoint.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AddressEntity saveAddress(AddressEntity addressEntity, CustomerAddressEntity customerAddressEntity) throws SaveAddressException {
		if(addressEntity.getCity().length()==0||addressEntity.getFlatBuilNo().length()==0||addressEntity.getLocality().length()==0||addressEntity.getUuid().length()==0||addressEntity.getPincode().length()==0)
			throw new SaveAddressException("SAR-001","No field can be empty");
		if(!isPincodeValid(addressEntity.getPincode()))
			throw new SaveAddressException("SAR-002","Invalid pincode");
		if(getStateByUUID(addressEntity.getUuid())==null)
			throw new SaveAddressException("ANF-002","No state by this id");
		else
			addressEntity.setState(getStateByUUID(addressEntity.getUuid()));
		addressEntity = addressDao.saveAddress(addressEntity);
			return addressEntity;
	}

	public boolean isPincodeValid(String pin){
		Pattern p = Pattern.compile("[0-9]{6}");
		if(pin.length()!=6)
			return false;
		return p.matcher(pin).matches();
	}


	/**
	 * The method implements the business logic for get address by uuid endpoint.
	 */
	@Override
	public AddressEntity getAddressByUUID(String addressId, CustomerEntity customerEntity) throws AuthorizationFailedException, AddressNotFoundException {
		return addressDao.getAddressByUUID(addressId);
	}


	/**
	 * The method implements the business logic for saving customer address.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public CustomerAddressEntity saveCustomerAddress(CustomerAddressEntity customerAddressEntity) throws SaveAddressException{
		AddressEntity addressEntity = customerAddressEntity.getAddress();
		addressEntity = saveAddress(addressEntity,customerAddressEntity);
		customerAddressEntity.setAddress(addressEntity);
		return customerAddressEntity;
	}

	/**
	 * The method implements the business logic for delete address endpoint.
	 */
	@Override @Transactional(propagation = Propagation.REQUIRED)
	public AddressEntity deleteAddress(AddressEntity addressEntity) throws AddressNotFoundException{
		if(addressEntity.getUuid().length()==0)
			throw new AddressNotFoundException("ANF-005","Address id can not be empty");
		return addressDao.deleteAddress(addressEntity);
	}

	/**
	 * The method implements the business logic for getting all saved address endpoint.
	 */
	@Override public List<AddressEntity> getAllAddress(CustomerEntity customer) {
		return addressDao.getAllAddress(customer);
	}

	/**
	 * The method implements the business logic for getting state by id.
	 */
	@Override public StateEntity getStateByUUID(String uuid) {
		StateEntity stateByUUID=addressDao.getStateByUUID(uuid);
		return stateByUUID;
	}


}
