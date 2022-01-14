package by.iTechArt.servlets;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.LibUser;
import by.iTechArt.service.impl.LibUserService;
import by.iTechArt.validator.BirthValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LibUserServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(LibUserServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewLibUserForm(request, response);
                break;
            case "/insert":
                insertLibUser(request, response);
                break;
            case "/delete":
                deleteLibUser(request, response);
                break;
            case "/edit":
                showEditLibUserForm(request, response);
                break;
            case "/update":
                updateLibUser(request, response);
                break;
            default:
                getLibUserList(request, response);
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewLibUserForm(request, response);
                break;
            case "/insert":
                insertLibUser(request, response);
                break;
            case "/delete":
                deleteLibUser(request, response);
                break;
            case "/edit":
                showEditLibUserForm(request, response);
                break;
            case "/update":
                updateLibUser(request, response);
                break;
            default:
                getLibUserList(request, response);
                break;
        }
    }

    private void getLibUserList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/libUserList.jsp");
        LibUserService libUserService = new LibUserService();
        if (request.getAttribute("libUsersFromServer") == null) {
            List<LibUser> libUsers = null;
            try {
                libUsers = libUserService.showLibUserList();
            } catch (DAOException | SQLException e) {
                logger.info(e.getMessage());
            }
            request.setAttribute("libUsersFromServer", libUsers);

        }
        //RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/libUserList.jsp");
       dispatcher.forward(request, response);
    }
    private void showNewLibUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/libUserForm.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditLibUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LibUserService libUserService = new LibUserService();
        LibUser realLibUser = new LibUser();
        try {
            realLibUser = libUserService.searchById(id);
        } catch (DAOException | SQLException e) {
            logger.info(e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/libUserForm.jsp");
        request.setAttribute("libUser", realLibUser);
        dispatcher.forward(request, response);
    }
    private void insertLibUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LibUserService libUserService = new LibUserService();
        String name = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");
        String passportID = request.getParameter("passportID");
        String address = request.getParameter("address");
        String dateOfBirthString = request.getParameter("dateOfBirth");
        BirthValidator birthValidator = new BirthValidator();
        if(!birthValidator.isValid(dateOfBirthString)){
            logger.info("Use birthday format yyyy-MM-dd");
            throw new ServletException();
        }
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        String email = request.getParameter("email");
        LibUser libUser = new LibUser(name, surname, middlename, passportID, address, dateOfBirth, email);
        try {
            libUserService.addLibUser(libUser);
        } catch (DAOException | SQLException e) {
            logger.info(e.getMessage());
        }
        response.sendRedirect("list");
        //response.sendRedirect("/libUserServlet");
    }
    private void updateLibUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LibUserService libUserService = new LibUserService();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");
        String passportID = request.getParameter("passportID");
        String address = request.getParameter("address");
        String dateOfBirthString = request.getParameter("dateOfBirth");
        BirthValidator birthValidator = new BirthValidator();
        if(!birthValidator.isValid(dateOfBirthString)){
            logger.info("Use birthday format yyyy-MM-dd");
            throw new ServletException();
        }
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        String email = request.getParameter("email");
        LibUser libUser = new LibUser(name, surname, middlename, passportID, address, dateOfBirth, email);
        try {
            libUserService.editLibUser(id,libUser);
        } catch (DAOException | SQLException e) {
            logger.info(e.getMessage());
        }
       // response.sendRedirect("/libUserServlet");
        response.sendRedirect("list");
    }
    private void deleteLibUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LibUserService libUserService = new LibUserService();
        try {
            libUserService.deleteLibUser(id);
        } catch (DAOException | SQLException e) {
            logger.info(e.getMessage());
        }
        response.sendRedirect("list");
        //response.sendRedirect("/libUserServlet");
    }

    }
