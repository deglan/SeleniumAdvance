package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDetails {

    private String reference;
    private String date;
    private String total;
    private String payment;
    private String status;
}
