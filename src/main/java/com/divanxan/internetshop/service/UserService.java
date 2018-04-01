package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * This class is a service class for the UserController
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Service("userService")
public class UserService {

    private final UserDao userDao;

    private final HttpSession session;

    @Autowired
    public UserService(UserDao userDao, HttpSession session) {
        this.userDao = userDao;
        this.session = session;
    }

    /**
     * This method is used to get the user from the database
     *
     * @return String
     */
    public User getUser() {

        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        return userDao.getByEmail(email);
    }

    /**
     * This method serves to obtain user alces from the database
     *
     * @param userId - id of User
     * @return List<Address> - list addresses of User
     */
    public List<Address> getAddresses(int userId) {
        return userDao.listAddressess(userId);
    }

    /**
     * This method serves to retrieve user orders from the database
     *
     * @param userId - id of User
     * @return List<OrderDetail>
     */
    public List<OrderDetail> getOrders(int userId) {
        return userDao.listOrders(userId);
    }

    /**
     * Getting billing address of user
     *
     * @param userId - id of User
     * @return Address - billing address of Useer
     */
    public Address getBillingAddress(int userId) {
        return userDao.getBillingAddress(userId);
    }

    /**
     * Getting address id from session
     *
     * @return int - address id
     */
    private int getAddressId() {
        return (int) session.getAttribute("addressId");
    }

    /**
     * Getting address by id
     *
     * @param addressId - id of address
     * @return Address - getting by id
     */
    private Address getAddres(int addressId) {
        return userDao.getAddress(addressId);
    }

    /**
     * Updating user
     *
     * @param user - updating User
     */
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * Getting email from session
     *
     * @return String - email
     */
    public String getEmail() {
        return ((UserModel) session.getAttribute("userModel")).getEmail();
    }

    /**
     * Setting address id
     *
     * @param userAddressId - setting address id
     */
    public void setAddressId(int userAddressId) {
        session.setAttribute("addressId", userAddressId);
    }

    /**
     * Validation user information for changed information
     *
     * @param map - Map<String, String> with data
     * @param model - model from view
     * @return String with redirect information
     */
    public String ValidateUserInformation(Map<String, String> map, Model model) {
        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String email = map.get("email");
        String contactNumber = map.get("contactNumber");

        String password1 = map.get("password1");
        String password2 = map.get("password2");
        String password3 = map.get("password3");

        String addressLineOne = map.get("addressLineOne");
        String addressLineTwo = map.get("addressLineTwo");
        String city = map.get("city");
        String state = map.get("region");
        String country = map.get("country");
        String postalCode = map.get("postalCode");


        User user = this.getUser();

        int addressId = -1;
        if (addressLineOne != null) this.getAddressId();

//        List<Address> addresses = userService.getAddresses(user.getId());


        Address address = null;
        if (addressLineOne != null) address = this.getAddres(addressId);

//        for (Address adr: addresses) {
//            if(adr.getId()==addressId) address = adr;
//        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        // boolean whatIf = BCrypt.checkpw(password3, user.getPassword());

        if (addressLineOne != null) {
            if (addressLineOne.equals("") && addressLineTwo.equals("") && city.equals("")
                    && state.equals("") && country.equals("") && postalCode.equals("")) {
                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noAddress";
            }

        }
        if (firstName != null) {
            if (firstName.equals("") && lastName.equals("") && email.equals("") && contactNumber.equals("")) {

                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noUser";
            }
        }
        if (password1 != null) {
            if (password2.equals("") || password3.equals("") || !password1.equals(password2)
                    || !BCrypt.checkpw(password3, user.getPassword())) {
                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noPassword";
            }
        }


        if (firstName != null && !firstName.equals("")) user.setFirstName(firstName);
        if (lastName != null && !lastName.equals("")) user.setLastName(lastName);
        if (email != null && !email.equals("")) user.setEmail(email);
        if (contactNumber != null && !contactNumber.equals("")) user.setContactNumber(contactNumber);

        if (password1 != null && !password1.equals("")) user.setPassword(passwordEncoder.encode(password1));

        if (addressLineOne != null && !addressLineOne.equals("")) address.setAddressLineOne(addressLineOne);
        if (addressLineTwo != null && !addressLineTwo.equals("")) address.setAddressLineTwo(addressLineTwo);
        if (city != null && !city.equals("")) address.setCity(city);
        if (state != null && !state.equals("")) address.setState(state);
        if (country != null && !country.equals("")) address.setCountry(country);
        if (postalCode != null && !postalCode.equals("")) address.setPostalCode(postalCode);

        if (firstName != null || password1 != null) {
            userDao.update(user);
            UserModel userModel = ((UserModel) session.getAttribute("userModel"));
            userModel.setEmail(user.getEmail());
            userModel.setFullName(user.getFirstName() + " " + user.getLastName());

            session.setAttribute("userModel", userModel);
        }

        if (addressLineOne != null) {
            userDao.updateAddress(address);
        }

        return "redirect:/user/show?operation=user";
    }

    /**
     * Getting login coont
     *
     * @return int - count of trying login
     */
    public int getLoginCount() {
        UserModel userModel = ((UserModel) session.getAttribute("userModel"));
        return userModel.getLoginCount();
    }

    /**
     * Saving login count
     *
     * @param count - login count
     */
    public void setLoginCount(int count) {
        UserModel userModel = ((UserModel) session.getAttribute("userModel"));
        userModel.setLoginCount(count);
    }
}
