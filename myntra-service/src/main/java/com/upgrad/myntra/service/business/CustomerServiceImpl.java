package com.upgrad.myntra.service.business;


import com.upgrad.myntra.service.dao.CustomerDao;
import com.upgrad.myntra.service.entity.CustomerAuthEntity;
import com.upgrad.myntra.service.entity.CustomerEntity;
import com.upgrad.myntra.service.exception.AuthenticationFailedException;
import com.upgrad.myntra.service.exception.AuthorizationFailedException;
import com.upgrad.myntra.service.exception.SignUpRestrictedException;
import com.upgrad.myntra.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    /**
     * The method implements the business logic for saving customer details endpoint.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) throws SignUpRestrictedException {
        if(customerDao.getCustomerByContactNumber(customerEntity.getContactNumber())==null)
            throw new SignUpRestrictedException("SGR-001","This contact number is already registered! Try other contact number.");
        if(!colFilled(customerEntity))
            throw new SignUpRestrictedException("SGR-005","Except last name all fields should be filled");
        if(!isEmailValid(customerEntity.getEmail()))
            throw new SignUpRestrictedException("SGR-002","Invalid email-id format!");
        if(!isMobileCorrect(customerEntity.getContactNumber()))
            throw new SignUpRestrictedException("SGR-003","Invalid contact number!");
        if(!passwordValidation(customerEntity.getPassword()))
            throw new SignUpRestrictedException("SGR-004","Weak password!");
        String[] encrypted = passwordCryptographyProvider.encrypt(customerEntity.getPassword());
        customerEntity.setPassword(encrypted[1]);
        customerEntity.setSalt(encrypted[0]);

        return customerDao.saveCustomer(customerEntity);
    }

    public boolean colFilled(CustomerEntity customerEntity){
        if(customerEntity.getUuid().length()==0||customerEntity.getPassword().length()==0||customerEntity.getFirstName().length()==0||customerEntity.getEmail().length()==0||customerEntity.getContactNumber().length()==0)
            return false;
        else
            return true;
    }
    public boolean passwordValidation(String pass){
        if(pass.matches(".*[0-9]{1,}.*") && pass.matches(".*[#@$%&*!^]{1,}.*") &&pass.matches(".*[A-Z]{1,}.*")&& pass.length()>=8 )
            return true;
        else
            return false;
    }
    public boolean isMobileCorrect(String mob){
        Pattern p = Pattern.compile("[0-9]{10}");
        if(mob.length()!=10)
            return false;
        return p.matcher(mob).matches();
    }
    public boolean isEmailValid(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    /**
     * The method implements the business logic for signin endpoint.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity authenticate(String contactNumber, String password) throws AuthenticationFailedException {
        CustomerEntity customerEntity = customerDao.getCustomerByContactNumber(contactNumber);
        if (customerEntity == null) {
            throw new AuthenticationFailedException("AUTH-001", "This contact number has not been registered!");
        } else {

            String encryptedPassword = passwordCryptographyProvider.encrypt(password, customerEntity.getSalt());
            if (customerEntity.getPassword().equals(encryptedPassword)) {

                JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
                CustomerAuthEntity userAuthTokenEntity = new CustomerAuthEntity();
                userAuthTokenEntity.setUuid(UUID.randomUUID().toString());
                userAuthTokenEntity.setCustomer(customerEntity);
                ZonedDateTime now = ZonedDateTime.now();
                ZonedDateTime expiry = now.plusHours(8);
                userAuthTokenEntity.setLoginAt(now);
                userAuthTokenEntity.setExpiresAt(expiry);
                String accessToken = jwtTokenProvider.generateToken(customerEntity.getUuid(), now, expiry);
                userAuthTokenEntity.setAccessToken(accessToken);
                customerDao.updateCustomerAuth(userAuthTokenEntity);
                customerDao.updateCustomer(customerEntity);
                //return UserAuthTokenEntity so generated
                return userAuthTokenEntity;

            } else {
                throw new AuthenticationFailedException("AUTH-002", "Invalid Credentials");

            }
        }
    }

    /**
     * The method implements the business logic for logout endpoint.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity logout(String access_token) throws AuthorizationFailedException {

        CustomerAuthEntity customerAuthEntity = customerDao.getCustomerAuthByAccesstoken(access_token);
        authorization(access_token);
        customerAuthEntity.setLogoutAt(ZonedDateTime.now());
        return customerDao.updateCustomerAuth(customerAuthEntity);
    }

    /**
     * The method implements the business logic for updating customer password endpoint.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomerPassword(String oldPassword,String newPassword, CustomerEntity customerEntity) throws UpdateCustomerException {
        final String encryptedOldPassword = PasswordCryptographyProvider.encrypt(oldPassword, customerEntity.getSalt());
        String newpass=passwordCryptographyProvider.encrypt(newPassword,customerEntity.getSalt());
        System.out.println("HOOOOOOOOOOOOOOOOOO "+customerEntity.getPassword()+" "+oldPassword);
        if(oldPassword.length()==0||newPassword.length()==0)
            throw new UpdateCustomerException("UCR-003","No field should be empty");
        if(!passwordValidation(newPassword))
            throw new UpdateCustomerException("UCR-001","Weak password!");
        if(!customerEntity.getPassword().equals(encryptedOldPassword))
            throw new UpdateCustomerException("UCR-004","Incorrect old password!");
        customerEntity.setPassword(newpass);
        customerDao.updateCustomer(customerEntity);

        return customerEntity;
    }


    /**
     * The method implements the business logic for checking authorization of any customer.
     */
    @Override
    public void authorization(String access_token) throws AuthorizationFailedException {

        CustomerAuthEntity customerAuthEntity = customerDao.getCustomerAuthByAccesstoken(access_token);
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("AUTH-001", "Customer is not Logged in.");
        }
        else {
            ZonedDateTime now = ZonedDateTime.now();
            long difference = customerAuthEntity.getExpiresAt().compareTo(now);
            if (customerAuthEntity.getLoginAt() != null) {
                throw new AuthorizationFailedException("AUTH-002", "Customer is logged out. Log in again to access this endpoint.");
            }
            else if (difference < 0) {
                throw new AuthorizationFailedException("AUTH-003", "Your session is expired. Log in again to access this endpoint.");
            }
        }
    }

    /**
     * The method implements the business logic for getting customer details by access token.
     */
    @Override
    public CustomerEntity getCustomer(String access_token) throws AuthorizationFailedException {

        authorization(access_token);
        CustomerAuthEntity customerAuthEntity = customerDao.getCustomerAuthByAccesstoken(access_token);
        return customerAuthEntity.getCustomer();
    }
}
