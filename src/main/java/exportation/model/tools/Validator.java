package exportation.model.tools;

import java.util.regex.Pattern;

public class Validator {

    // USER_TABLE Validators
    public static String usernameValidator(String username, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z0-9_]{3,30}$", username)) {
            return username;
        } else {
            throw new Exception(message);
        }
    }

    public static String passwordValidator(String password, String message) throws Exception {
        if (password.length() >= 8 && password.length() <= 30) {
            return password;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean userEnabledValidator(int userEnabled, String message) throws Exception {
        if (userEnabled == 0 || userEnabled == 1) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    // PERSON_TABLE Validators
    public static String nameValidator(String name, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name)) {
            return name;
        } else {
            throw new Exception(message);
        }
    }

    public static String genderValidator(String gender, String message) throws Exception {
        if (gender.equals("male") || gender.equals("female")) {
            return gender;
        } else {
            throw new Exception(message);
        }
    }

    public static String nationalIdValidator(String nationalId, String message) throws Exception {
        if (Pattern.matches("^\\d{10}$", nationalId)) {
            return nationalId;
        } else {
            throw new Exception(message);
        }
    }

    public static String phoneNumberValidator(String phoneNumber, String message) throws Exception {
        if (Pattern.matches("^\\d{11}$", phoneNumber)) {
            return phoneNumber;
        } else {
            throw new Exception(message);
        }
    }

    public static String emailValidator(String email, String message) throws Exception {
        if (Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
            return email;
        } else {
            throw new Exception(message);
        }
    }

    public static String addressValidator(String address, String message) throws Exception {
        if (address.length() <= 300) {
            return address;
        } else {
            throw new Exception(message);
        }
    }

    public static String positionValidator(String position, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", position)) {
            return position;
        } else {
            throw new Exception(message);
        }
    }

    // COUNTRY_TABLE Validators
    public static String countryNameValidator(String countryName, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", countryName)) {
            return countryName;
        } else {
            throw new Exception(message);
        }
    }

    public static String tariffValidator(String tariff, String message) throws Exception {
        if (Pattern.matches("^\\d{1,4}$", tariff)) {
            return tariff;
        } else {
            throw new Exception(message);
        }
    }

    public static String phoneCodeValidator(String phoneCode, String message) throws Exception {
        if (Pattern.matches("^(\\d{4}|\\+\\d{2}|00\\d{2})$", phoneCode)) {
            return phoneCode;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean rateValidator(double rate, String message) throws Exception {
        if (rate >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean populationValidator(long population, String message) throws Exception {
        if (population >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static String neighborsValidator(String neighbors, String message) throws Exception {
        if (neighbors.length() <= 30) {
            return neighbors;
        } else {
            throw new Exception(message);
        }
    }

    // COMPANY_TABLE Validators
    public static String companyNameValidator(String companyName, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", companyName)) {
            return companyName;
        } else {
            throw new Exception(message);
        }
    }

    public static String productValidator(String product, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", product)) {
            return product;
        } else {
            throw new Exception(message);
        }
    }

    public static String companyAddressValidator(String address, String message) throws Exception {
        if (address.length() <= 250) {
            return address;
        } else {
            throw new Exception(message);
        }
    }

    public static String companyEmailValidator(String email, String message) throws Exception {
        if (Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
            return email;
        } else {
            throw new Exception(message);
        }
    }

    public static String companyPhoneValidator(String phone, String message) throws Exception {
        if (Pattern.matches("^\\d{11}$", phone)) {
            return phone;
        } else {
            throw new Exception(message);
        }
    }

    public static String companyTypeValidator(String type, String message) throws Exception {
        if (type.equals("supplier") || type.equals("manufacturer")) {
            return type;
        } else {
            throw new Exception(message);
        }
    }

    // TRADE_TABLE Validators
    public static String tradeStatusValidator(String status, String message) throws Exception {
        if (status.length() <= 30) {
            return status;
        } else {
            throw new Exception(message);
        }
    }

    public static String contractValidator(String contract, String message) throws Exception {
        if (contract.length() <= 500) {
            return contract;
        } else {
            throw new Exception(message);
        }
    }

    public static String agreementValidator(String agreement, String message) throws Exception {
        if (agreement.length() <= 500) {
            return agreement;
        } else {
            throw new Exception(message);
        }
    }

    // ITEM_TABLE Validators
    public static String itemNameValidator(String itemName, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z0-9\\s]{3,40}$", itemName)) {
            return itemName;
        } else {
            throw new Exception(message);
        }
    }

    public static String itemBrandValidator(String itemBrand, String message) throws Exception {
        if (itemBrand.equals("hipile") || itemBrand.equals("carpile") || itemBrand.equals("handle") || itemBrand.equals("tino")) {
            return itemBrand;
        } else {
            throw new Exception(message);
        }
    }

    public static String itemModelValidator(String itemModel, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z0-9\\s]{1,40}$", itemModel)) {
            return itemModel;
        } else {
            throw new Exception(message);
        }
    }

    public static String dimensionValidator(String dimension, String message) throws Exception {
        if (dimension.length() <= 40) {
            return dimension;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean capacityValidator(int capacity, String message) throws Exception {
        if (capacity >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean costValidator(double cost, String message) throws Exception {
        if (cost >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean weightValidator(double weight, String message) throws Exception {
        if (weight >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    // TRANSPORTATION_TABLE Validators
    public static String transportationDirectionValidator(String direction, String message) throws Exception {
        if (direction.length() <= 30) {
            return direction;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean freightValidator(double freight, String message) throws Exception {
        if (freight >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    // EXPORTTRACING_TABLE Validators
    public static boolean loadingStatusValidator(int status, String message) throws Exception {
        if (status == 0 || status == 1) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean prepaymentValidator(int prepayment, String message) throws Exception {
        if (prepayment == 0 || prepayment == 1) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean checkoutValidator(int checkout, String message) throws Exception {
        if (checkout == 0 || checkout == 1) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    // PAYMENT_TABLE Validators
    public static boolean taxValidator(double tax, String message) throws Exception {
        if (tax >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }

    public static boolean insuranceValidator(double insurance, String message) throws Exception {
        if (insurance >= 0) {
            return true;
        } else {
            throw new Exception(message);
        }
    }
}
