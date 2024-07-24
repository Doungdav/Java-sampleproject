package utils;

import model.Product;
import repository.ProductRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ValidationUtils {
    public static int readInteger(String message, Scanner input, boolean isExisted){
        while (true)
        try{
            System.out.println(message);
            int productID = input.nextInt();
            if(isExisted) {
                List<Product> allProducts = ProductRepository.getAllProducts();
                //check if the id exist or not
                int existedID = allProducts.stream()
                        .map(Product::getProductId)
                        .filter(id -> id == productID).findFirst()
                        .orElse(0);
                if (existedID != 0) {
                    throw new Exception("ERROR !! ID ALREADY EXISTED!!");
                }
            }
                return productID;
        }catch (InputMismatchException ex){
            System.out.println("ERROR !! CAN ONLY INPUT NUMBER !!");
            input.nextLine();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            input.nextLine();
        }

    }
    public static float readFloat(String message, Scanner input){

        while (true)
            try{
                System.out.println(message);
                return input.nextFloat();
            }catch (InputMismatchException ex){
                System.out.println("ERROR !! CAN ONLY INPUT NUMBER !!");
                input.nextLine();
            }

    }
    public static Date readSqlDate(String message, Scanner input){
        while (true){
            try{
                System.out.println(message);
                String stringDate = input.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(stringDate, formatter);
                return Date.valueOf(date);

            }catch (DateTimeParseException ex){
                System.out.println("ERROR!! DATE MUST FOLLOW (yyyy-MMM-dd) FORMAT!! ");
            }
        }
        }




}


