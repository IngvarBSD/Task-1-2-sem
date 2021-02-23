package ru.vsu.cs.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import ru.vsu.cs.common.PaymentMethod;
import javafx.fxml.FXML;

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
        paymentMethod.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)paymentMethod.getSelectedToggle();
                if(rb != null) {
                    if(rb == radioBtn_1)
                        calculator.setCredit(PaymentMethod.DIFFERENTIATED_PAYMENT);
                    if(rb == radioBtn_2)
                        calculator.setCredit(PaymentMethod.ANNUITY_PAYMENT);
                }
            }
        });

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
            try {
                s = Double.parseDouble(sum);
                dL = Integer.parseInt(loanTerm);
                iR = Double.parseDouble(interRate);
            } catch (NumberFormatException ex) {

            }
            calculator.calculateTotalSumOfPay(s, dL, iR);
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
                try{
                    s = Double.parseDouble(sum);
                    dL = Integer.parseInt(loanTerm);
                    iR = Double.parseDouble(interRate);
                    n = Integer.parseInt(nMonth);
                }catch(NumberFormatException ex) {

                }
                calculator.calculateSumOfPayForNMonth(n, s, dL, iR);
                displayLabel(calculator.getResult());
            }
        }

}
