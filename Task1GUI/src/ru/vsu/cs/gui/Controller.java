package ru.vsu.cs.gui;

import ru.vsu.cs.common.PaymentMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller extends View {

    private CreditCalculator calculator = new CreditCalculator();

    @FXML
    private TextField TxFieldCreditSum;

    @FXML
    private TextField TxFieldLoanTerm;

    @FXML
    private TextField TxFieldInterRate;

    @FXML
    private TextField TxFieldInpMonth;

    @FXML
    private Button ButtonTotalPay;

    @FXML
    private Button ButtonMonthPayment;

    @FXML
    private RadioButton radioBtn_1;

    @FXML
    private RadioButton radioBtn_2;

    @FXML
    private ToggleGroup paymentMethod;

    @FXML
    void initialize() {
        radioBtn_1.setToggleGroup(paymentMethod);
        radioBtn_2.setToggleGroup(paymentMethod);

        ButtonTotalPay.setOnAction(actionEvent -> {
              onActionButtonTotalPay();
        });

        ButtonMonthPayment.setOnAction(actionEvent -> {
           onActionButtonMonthPayment();
        });
    }

    @FXML
    public void onActionButtonTotalPay() {
        String sum = TxFieldCreditSum.getText();
        String loanTerm = TxFieldLoanTerm.getText();
        String interRate = TxFieldInterRate.getText();
        if (sum.length() != 0 && loanTerm.length() != 0 && interRate.length() != 0 && paymentMethod.getSelectedToggle() != null) {
            double s = 0;
            int dL = 0;
            double iR = 0;
            PaymentMethods payMethod;
            if (paymentMethod.getSelectedToggle() == radioBtn_1)
                payMethod = PaymentMethods.differentiatedPayments;
            else
                payMethod = PaymentMethods.annuityPayments;
            try {
                s = Double.parseDouble(sum);
                dL = Integer.parseInt(loanTerm);
                iR = Double.parseDouble(interRate);
            } catch (NumberFormatException ex) {

            }

            calculator.setCredit(s, dL, iR, payMethod);
            calculator.calculateTotalSumOfPay();
            displayLabel(calculator.getResult());
        }
    }

    @FXML
    public void onActionButtonMonthPayment() {
            String sum = TxFieldCreditSum.getText();
            String loanTerm = TxFieldLoanTerm.getText();
            String interRate = TxFieldInterRate.getText();
            String nMonth = TxFieldInpMonth.getText();

            if(sum.length() != 0 && loanTerm.length() != 0 && interRate.length() != 0 && nMonth.length() != 0 && paymentMethod.getSelectedToggle() != null) {
                double s = 0;
                int dL = 0;
                double iR = 0;
                int n = 0;
                PaymentMethods payMethod;
                if (paymentMethod.getSelectedToggle() == radioBtn_1)
                    payMethod = PaymentMethods.differentiatedPayments;
                else
                    payMethod = PaymentMethods.annuityPayments;
                try{
                    s = Double.parseDouble(sum);
                    dL = Integer.parseInt(loanTerm);
                    iR = Double.parseDouble(interRate);
                    n = Integer.parseInt(nMonth);
                }catch(NumberFormatException ex) {

                }

                calculator.setCredit(s, dL, iR, payMethod);
                calculator.calculateSumOfPayForNMonth(n);
                displayLabel(calculator.getResult());
            }
        }
}
