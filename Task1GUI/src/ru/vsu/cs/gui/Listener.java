package ru.vsu.cs.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import ru.vsu.cs.common.CommonCredit;
import ru.vsu.cs.common.PaymentMethod;
import javafx.fxml.FXML;

public class Listener {

    private CommonCredit credit;

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
    private Label resultLabel;

    @FXML
    void initialize() {
        radioBtn_1.setToggleGroup(paymentMethod);
        radioBtn_2.setToggleGroup(paymentMethod);
        paymentMethod.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton)paymentMethod.getSelectedToggle();
                if(rb != null) {
                    PaymentMethod pm;
                    if (rb == radioBtn_1) {
                        pm = PaymentMethod.DIFFERENTIATED_PAYMENT;
                        credit = pm.action();
                    }
                    if(rb == radioBtn_2) {
                        pm = PaymentMethod.ANNUITY_PAYMENT;
                        credit = pm.action();
                    }
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
            resultLabel.setText(Double.toString(credit.getTotalSumOfPay(s, dL, iR)));
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
                resultLabel.setText(Double.toString(credit.getSumOfPayForNMonth(n, s, dL, iR)));
            }
        }
}