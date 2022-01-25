package by.iTechArt.validator;

import by.iTechArt.models.Book;
import by.iTechArt.models.LibUser;
import by.iTechArt.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static java.util.Objects.isNull;


public class ServiceValidator {

    static final Logger logger = LogManager.getLogger(ServiceValidator.class);

    private final NameValidator nameValidator;
    private final EmailValidator emailValidator;
    private final PassportIdValidator passportIdValidator;
    public ServiceValidator() {
        this.emailValidator = new EmailValidator();
        this.nameValidator = new NameValidator();
        this.passportIdValidator = new PassportIdValidator();
    }

    public void validationLibUserFields(LibUser libUser) throws ServiceException {
        validationNull(libUser);
        if(!nameValidator.isValid(libUser.getFirstname())){
            logger.info("First name should consist of letters");
            throw new ServiceException();
        }
        if(!nameValidator.isValid(libUser.getSurname())){
            logger.info("Surname should consist of letters");
            throw new ServiceException();
        }
        if(!emailValidator.isValid(libUser.getEmail())){
            logger.info("Email should be like [A-Za-z0-9+_.-]+@[A-Za-z0-9.-]");
            throw new ServiceException();
        }
        if( !("".equals(libUser.getPassportID()))
                && !passportIdValidator.isValid(libUser.getPassportID()) ){
            logger.info("Passport should be like MP\\d{7}");
            throw new ServiceException();
        }
    }
    public void validationBook(Book book) throws ServiceException {
        if (isNull(book) || ("".equals(book.getNameRu()))
                || ("".equals(book.getCost()))
        ) {
            logger.info("NameRU, Cost should not be empty.");
            throw new ServiceException("Invalid Book");
        }
    }

    private void validationNull(LibUser libUser) throws ServiceException {
        if (isNull(libUser) || ("".equals(libUser.getFirstname()))
                || ("".equals(libUser.getSurname()))
                || ("".equals(libUser.getEmail()))
        ) {
            logger.info("Name, Surname, Email should not be empty.");
            throw new ServiceException("Invalid User");
        }
    }

    public void validationUnique(LibUser libUser, List<LibUser> userList) throws ServiceException {
        for (LibUser lu: userList) {
           if(!("".equals(libUser.getPassportID()))
                   && ( lu.getPassportID().equals(libUser.getPassportID()) ) ){
               logger.info("PassportID is not unique.");
               throw new ServiceException("Not unique passportID.");
           }
           if( lu.getEmail().equals(libUser.getEmail()) ){
               logger.info("Email is not unique.");
               throw new ServiceException("Not unique email.");
           }
        }
    }

//
//    public void validation(Book book) throws ServiceException {
//        if (isNull(book)) {
//            throw new ServiceException("Null Book");
//        } else if ("".equals(book.getTitle()) && "".equals(book.getAuthor())) {
//            throw new ServiceException("invalid book");
//        }
//    }

//    public void validation(long id) throws ServiceException {
//        if (id < 0) {
//            throw new ServiceException("Invalid id.");
//        }
//    }
//
//    public void validation(String string) throws ServiceException {
//        if (isNull(string) || string.isEmpty()) {
//            throw new ServiceException("Invalid string.");
//        }
//    }
//
//    public void validation(List<?> parametersList) throws ServiceException {
//        if (isNull(parametersList) || parametersList.isEmpty()) {
//            throw new ServiceException("Parameters list is null or empty");
//        }
//    }

}
