import model.Product;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.w3c.dom.ls.LSOutput;
import repository.ProductRepository;
import repository.UserRepository;
import utils.TableUtils;
import utils.ValidationUtils;

import java.util.*;

public class ProductManagementSystem {

    public static void PressAnyKey() {
        Scanner input = new Scanner(System.in);
        System.out.println("_________________________________");
        System.out.println("    Press Enter to Continue");
        System.out.println("_________________________________");
        input.nextLine();
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;
        List<String> mainMenu = new ArrayList<>(List.of(
                "Add new Product",
                "Edit Product",
                "Delete Product",
                "Show Product",
                "Search Product",
                "Exit Program"
        ));
        //login first in order to get into program
        boolean isAuthenticated = true;
      /*  while (true){
            String username;
            String password;
            List<User> allUsers = UserRepository.getAllUsers();
            TableUtils.renderMenu(null,"User Authentication");
//            System.out.println("All Users : " + allUsers);
            System.out.println("Enter Username : ");
            username = input.nextLine();
            System.out.println("Enter Password : ");
            password = input.nextLine();

            Optional<User> loginUser = allUsers.stream().filter(
                    user -> (user.getUsername().equals(username)
                            &&
                            user.getPassword().equals(password))).findFirst();
            if(loginUser.isPresent()){
                System.out.println("Successfully login !!");
                isAuthenticated = true;
                break;
            }else{
                System.out.println("FAILED TO LOGIN!! WRONG CREDENTIALS!!");
                System.out.println("To Continue 'Yes' to Exit 'No' : ");
               String  exitOptions = input.nextLine();
               if (exitOptions.equalsIgnoreCase("no"))break;
            }

        }*/

        if (isAuthenticated) {
            do {
                TableUtils.renderMenu(mainMenu, "Product Management System");
                option = ValidationUtils.readInteger("Choose your option from 1-6:", input, false);
                switch (option) {
                    case 1: {
                        TableUtils.renderMenu(null, "Create new product:");
                        int result = ProductRepository.insertProduct(new Product().inputProductInfo(input));
                        if (result != 0) {
                            System.out.println("Successfully created !");
                        } else {
                            System.out.println("FAILED TO CREATE PRODUCT!");
                        }
                    }
                    break;
                    case 2: {
                        List<Product> allProduct = ProductRepository.getAllProducts();
                        if(allProduct == null || allProduct.size()==0){
                            TableUtils.renderProductData(null, "All Product Information");
                            break;
                        }
                        TableUtils.renderMenu(null, "Update product:");
                        int updateId = ValidationUtils.readInteger("Enter product to update:", input, false);
                        input.nextLine();
                        Optional<Product> findProduct = ProductRepository.getAllProducts()
                                .stream()
                                .filter(product -> product.getProductId() == updateId)
                                .findAny();
                        if (findProduct.isPresent()) {
                            int result = ProductRepository.updateProduct(new Product().updateProductInfo(input), updateId);
                            if (result != 0) {
                                System.out.println("Update Product Successfully !");
                            } else {
                                System.out.println("FAILED TO UPDATE PRODUCT!");
                            }
                        } else {
                            System.out.println("ID" + updateId + "DOESN'T EXIT !");
                        }
                    }
                    break;
                    case 3: {
                        List<Product> allProduct = ProductRepository.getAllProducts();
                        if(allProduct == null || allProduct.size()==0){
                            TableUtils.renderProductData(null, "All Product Information");
                            break;
                        }
                        TableUtils.renderMenu(null, "Delete product:");
                        int deleteId = ValidationUtils.readInteger("Enter product to delete:", input, false);
                        input.nextLine();
                        Optional<Product> findProduct = ProductRepository.getAllProducts()
                                .stream()
                                .filter(product -> product.getProductId() == deleteId)
                                .findAny();
                        if (findProduct.isPresent()) {
                            int result = ProductRepository.deleteProduct(deleteId);
                            if (result != 0) {
                                System.out.println("Update Product Successfully !");
                            } else {
                                System.out.println("FAILED TO UPDATE PRODUCT!");
                            }
                        } else {
                            System.out.println("ID" + deleteId + "DOESN'T EXIT !");
                        }
                    }
                    break;
                    case 4: {
                        List<String> showOptions = new ArrayList<>(
                                List.of("Show Ascending order (id):",
                                        "Show Descending order (id):",
                                        "Show Descending order (price)",
                                        "Show Ascending order (product name)",
                                        "exit"
                                )
                        );
                        int showOption;
                        do {
                            TableUtils.renderMenu(showOptions, "Show all product");
                            showOption = ValidationUtils.readInteger("Choose your options from 1-5:", input, false);
                            List<Product> allProducts = ProductRepository.getAllProducts();
                            switch (showOption) {
                                case 1:
                                    TableUtils.
                                            renderProductData(
                                                    allProducts
                                                            .stream()
                                                            .sorted(
                                                                    (a, b) -> a.getProductId() - b.getProductId()
                                                            ).toList(), "Show product in ascending order: ");
                                    break;
                                case 2:
                                    TableUtils.
                                            renderProductData(
                                                    allProducts
                                                            .stream()
                                                            .sorted(
                                                                    (a, b) -> b.getProductId() - a.getProductId()
                                                            ).toList(), "Show product in Descending order: ");
                                    break;
                                case 3:
                                    TableUtils.
                                            renderProductData(
                                                    allProducts
                                                            .stream()
                                                            .sorted(
                                                                    Comparator.comparingDouble(Product::getPrice).reversed()
                                                            ).toList(), "Show product price in descending order: ");
                                    break;
                                case 4:
                                    TableUtils.
                                    renderProductData(
                                            allProducts
                                                    .stream()
                                                    .sorted(
                                                            Comparator.comparing(Product::getProductName)
                                                    ).toList(), "Show product name in ascending order: ");
                                    break;
                                case 5:
                                    System.out.println("Exit Search Program!!");
                                    break;
                                default:
                                    System.out.println("INVALID OPTION ! CHOOSE AGAIN !!");
                            }
                        } while (showOption != 5);
                    }
                    break;
                    case 5: {
                        List<String> searchOptions = new ArrayList<>(
                                List.of("Search Product By ID ",
                                        "Search Product By Name ",
                                        "Search Product by Company",
                                        "exit"
                                )

                        );
                        int searchOption;
                        do{
                            TableUtils.renderMenu(searchOptions, "Search Product Option:");
                            searchOption = ValidationUtils.readInteger("Choose your option from 1-4",input,false);
                            List<Product> allProducts = ProductRepository.getAllProducts();
                            switch (searchOption){
                                case 1:
                                {
                                    TableUtils.renderMenu(null,"Search Product By Product Id");
                                    int productId = ValidationUtils.readInteger("Enter Product ID to Search:",input,false);
                                    TableUtils.renderProductData(allProducts
                                                    .stream()
                                                    .filter(product -> product.getProductId()==productId)
                                                    .toList(),
                                            "Search Product By ID");
                                }
                                    break;

                                case 2:
                                {
                                    TableUtils.renderMenu(null,"Search Product By Product Name");
                                    System.out.println("Enter Product name to search:");
                                    input.nextLine();
                                    String productName = input.nextLine();
                                    TableUtils.renderProductData(allProducts
                                            .stream()
                                            .filter(product -> product.getProductName().toLowerCase()
                                                    .startsWith(productName.toLowerCase()))
                                            .toList(), "Search Product By ID");
                                }
                                    break;
                                case 3:
                                {
                                    TableUtils.renderMenu(null,"Search Product By Company Name");
                                    System.out.println("Enter Company name to search:");
                                    input.nextLine();
                                    String companyName = input.nextLine();
                                    TableUtils.renderProductData(allProducts
                                            .stream()
                                            .filter(product -> product.getCompany().toLowerCase()
                                                    .startsWith(companyName.toLowerCase()))
                                            .toList(), "Search Product By Company");
                                }
                                    break;
                                case 4: break;
                                default:
                                    System.out.println("INVALID OPTION!! CHOOSE AGAIN!!");
                            }

                        }while (searchOption!=4);

                    }
                        break;
                    case 6:
                        System.out.println("Exit Program :");
                        break;
                    default:
                        System.out.println("ERROR !! INVALID OPTION !!");
                        break;
                }
                PressAnyKey();
            } while (option != 6);

        }


    }
}
