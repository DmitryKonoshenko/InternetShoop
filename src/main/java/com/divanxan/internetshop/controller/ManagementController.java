package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dto.Category;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.service.ManagerService;
import com.divanxan.internetshop.util.FileUploadUtility;
import com.divanxan.internetshop.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Controller for administrator management
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
@RequestMapping("/manage")
public class ManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    private final ManagerService managerService;

    /**
     * Constructor initializing the service class
     *
     * @param managerService
     */
    @Autowired
    public ManagementController(ManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     * Controller for showing all products
     *
     * @param operation String with parameter of last operation
     * @return ModelAndView
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "Product Management");
        mv.addObject("userClickManageProducts", true);

        Product nProduct = new Product();

        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        if (operation != null) {
            switch (operation) {
                case "product":
                    mv.addObject("message", "Товар успешно добавлен");
                    break;
                case "category":
                    mv.addObject("message", "Категория успешно добавлена");
                    break;
                case "notcategory":
                    mv.addObject("message", "Категория НЕ ДОБАВЛЕНА!!!");
                    break;
            }
        }
        logger.info(mv.toString());
        return mv;
    }

    /**
     * Controller for management product
     *
     * @param id of product
     * @return ModelAndView
     */
    @RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
    public ModelAndView showEditProducts(@PathVariable int id) {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "Product Management");
        mv.addObject("userClickManageProducts", true);

        Product nProduct = managerService.getProductById(id);

        mv.addObject("product", nProduct);
        return mv;
    }

    /**
     * Constructor for showing management result
     *
     * @param mProduct - product validation
     * @param result validation
     * @param model
     * @param request
     * @return String with redirect information
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String handleProductsSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result
            , Model model, HttpServletRequest request) {

        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, result);
        } else {
            if (!mProduct.getFile().getOriginalFilename().equals("")) {
                new ProductValidator().validate(mProduct, result);
            }
        }
//        new ProductValidator().validate(mProduct, result);

        // проверка на ошибки
        if (result.hasErrors()) {

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Product Management");
            model.addAttribute("message", "Ошибка валидации для добавления товара!");

            return "page";// если тут заюзать redirect:, то ошибки не будут выведены
        }


        logger.info(mProduct.toString());

        if (mProduct.getId() == 0) {
            // создание нового товара
            managerService.addProduct(mProduct);
        } else {
            // модификация товара
            managerService.updateProduct(mProduct);
        }

        if (!mProduct.getFile().getOriginalFilename().equals("")) {
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/product?operation=product";
    }

    /**
     * Controller for setting product in active or disabled mode
     *
     * @param id product
     */
    @RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
    @ResponseBody
    public void handleProductActivation(@PathVariable int id) {

        Product product = managerService.getProductById(id);
        boolean isActive = product.isActive();

        // активация или деактивация товара
        product.setActive(!product.isActive());

        managerService.updateProduct(product);
        logger.info("Product id: "+ id+". In active mod: "+isActive);

      //  if(isActive == true) ? "Товар успешно деактивирован" : "Товар успешно активирован";
    }

    /**
     * Controller for adding category
     *
     * @param category
     * @param result
     * @param model
     * @return String with redirect information
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String handleCategorySubmission(@Valid @ModelAttribute Category category, BindingResult result
            , Model model) {


        // проверка на ошибки
        if (result.hasErrors()) {

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Product Management");
            model.addAttribute("message", "Ошибка валидации для добавления категории!");

            return "redirect:/manage/product/?operation=notcategory";// если тут заюзать redirect:, то ошибки не будут выведены
        }


        logger.info(category.toString());

        if (category.getId() == 0) {
            // создание нового товара
            managerService.addCategory(category);
        } else {
            // модификация товара
            managerService.updateCategory(category);
        }

        //переходим в контроллер showManageProducts
        return "redirect:/manage/product/?operation=category";
    }

    /**
     * Controller for getting all category
     *
     * @return List<Category> - array with all category in shop
     */
    @ModelAttribute("categories")
    public List<Category> getCategories() {
        logger.info("Getting all category");
        return managerService.getListCategory();

    }

    /**
     * Controller for adding new category
     *
     * @return Category - new category
     */
    @ModelAttribute("category")
    public Category getCategory() {
        //берем новую категорию из manageProducts.jsp -> <sf:form modelAttribute="category" action="${contextRoot}/manage/category" method="POST"
        //                             class="form-horizontal">
        // и отправляем кго в handleCategorySubmission
        logger.info("Creating new category");
        return new Category();


    }

    /**
     * Controller for showing orders
     *
     * @param operation
     * @return ModelAndView
     */
    @RequestMapping("/orders")
    public ModelAndView showOrderDetail(@RequestParam(name = "operation", required = false) String operation) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("userClickManageOrders", true);
        mv.addObject("title", "Order Management");
        List<OrderDetail> list = managerService.getListAllOrders();

        mv.addObject("orderDetails", list);

        if (operation != null) {
            if (operation.equals("orderDetail")) {
                mv.addObject("message", "Заказ изменен");
            }
        }
        logger.info(mv.toString());
        return mv;
    }

    /**
     * Controller for show order detail
     *
     * @param id orderId
     * @return ModelAndView
     */
    @RequestMapping("/{id}/orderChange")
    public ModelAndView showEditOrder(@PathVariable int id) {

        OrderDetail orderDetail = null;
        List<OrderDetail> list = managerService.getListAllOrders();

        for (OrderDetail detail : list) {
            if (detail.getId() == id) {
                orderDetail = detail;
                break;
            }
        }


        ModelAndView mv = new ModelAndView("page");
        mv.addObject("userClickManageOrdersId", true);
        mv.addObject("title", "Order Management");
        if (orderDetail != null) {
            mv.addObject("orderDetail", orderDetail);
        }

        logger.info(mv.toString());
        return mv;
    }

    /**
     * Controller for management order
     *
     * @param map with order detail information
     * @return String with redirect information
     */
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String showOrderDetail(@RequestParam Map<String, String> map) {

        String delivered = map.get("delivered");
        String shipped = map.get("shipped");

        int orderId = Integer.parseInt(map.get("orderId"));

        OrderDetail orderDetail = null;
        List<OrderDetail> list = managerService.getListAllOrders();

        for (OrderDetail detail : list) {
            if (detail.getId() == orderId) {
                orderDetail = detail;
                break;
            }
        }

        if (delivered.equals("yes")) if (orderDetail != null) {
            orderDetail.setIsDelivery(true);
        }

        if (shipped.equals("yess")) if (orderDetail != null) {
            orderDetail.setShippedOrder(true);
        }

        managerService.updateOrderDetail(orderDetail);
        logger.info("Order detail changed: "+orderDetail.toString());
        return "redirect:/manage/orders?operation=orderDetail";
    }

    /**
     * Controller for showing statistic
     *
     * @return ModelAndView
     */
    @RequestMapping("/statistic")
    public ModelAndView showStatistic() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("userClickStatistic", true);

        List<Product> pList = managerService.getTopProducts();

        Map<BigDecimal, User> userList = managerService.getTopUsers();

        BigDecimal cashByMonth = managerService.getCashByMonth();

        BigDecimal cashByWeek = managerService.getCashByWeek();

        mv.addObject("cashByMonth", cashByMonth);
        mv.addObject("cashByWeek", cashByWeek);
        mv.addObject("listProducts", pList);
        mv.addObject("listUsers", userList);
        logger.info(mv.toString());
        return mv;

    }

}
