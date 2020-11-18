package CalculatorLogic;

import MainWindow.CalculaterShowController;

/**
 * Calculation logic. Control input operations and process them,
 * Also control what should be shown
 */
public class CalculatorController {


    private enum Statements{
        ENTER_SIGN,             // Wait some operation (+, - and etc...)
                                // Come from: ENTER_INT_NUMBER, ENTER_FLOAT_NUMBER
        ENTER_INT_NUMBER,       // Wait any number and `,` math point.
                                // Come from: ENTER_ANY_NUM
        ENTER_FLOAT_NUMBER,     // Wait any number, number already have `,` and ignore second
                                // Come from: ENTER_INT_NUMBER
        ENTER_ANY_NUM;          // Wait any number and `,` math point.
                                // Come from: ENTER_SIGN
    }

    // Hold new entered number
    private String singleEq = OperationController.EMPTY_STRING;
    private String prevInPlaceOP = "";
    private float finalNumber = 0.0f;
    private String operation = OperationController.PLUS;
    private Statements curState = Statements.ENTER_ANY_NUM;
    private CalculaterShowController shower;
    private boolean isInPlaceShown = false;

    public CalculatorController(CalculaterShowController shower){
        this.shower = shower;
    }

    public void sendSingleEq(String newSingleEq){
        // Clear all
        if (OperationController.isReset(newSingleEq)){
            this.resetAllControls();
            return;
        }

        // Some helpful bools
        // Some number were entered?
        boolean isSomeNumberWereEntered = curState == Statements.ENTER_INT_NUMBER ||
                curState == Statements.ENTER_FLOAT_NUMBER;
        // Cur state wait some number?
        boolean isWaitEnterNumber = isSomeNumberWereEntered ||
                                    curState == Statements.ENTER_ANY_NUM;
        // Cur state can have negative number?
        boolean isEnteredNumberNegative = curState == Statements.ENTER_ANY_NUM &&
                                          newSingleEq.equals(OperationController.MINUS);
        // Cur state can be floated?
        boolean isEnteredNumberFloat = newSingleEq.equals(OperationController.MATH_POINT) &&
                                       isWaitEnterNumber;

        // Reset entered number
        if (OperationController.isResetLast(newSingleEq)){
            // Delete only if something were entered
            if (isSomeNumberWereEntered){
                this.clearLastEnter();
            }
            return;
        }

        // Dodge second point in number
        if (isEnteredNumberFloat && curState == Statements.ENTER_FLOAT_NUMBER){
            return;
        }

        if (( (isNumeric(newSingleEq) || isEnteredNumberFloat) &&
               isWaitEnterNumber) ||
               isEnteredNumberNegative){

            this.processEnterNumber(newSingleEq, isEnteredNumberFloat);

        }else {

            if (OperationController.isOperationInPlace(newSingleEq)) {
                this.processOPInPlace(newSingleEq);
                return;
            }

            if (!OperationController.isGoodSign(newSingleEq)) {
                // Bad sign, get out of here (throw error)
                this.processBadSign(newSingleEq);
            }

            if (newSingleEq.equals(OperationController.EQUAL)) {
                // Calculate result what we have (i.e. end write number)
                this.calculateFullResult();
                return;

            }

            // Apply operation
            this.applyOP(newSingleEq);

        }

        this.sendDataToShower(newSingleEq);
    }

    private void sendDataToShower(String newSingleEq){
        if (this.curState == Statements.ENTER_ANY_NUM ){

            if (this.isInPlaceShown){
                this.shower.setFullEq( newSingleEq);
                this.isInPlaceShown = false;
            }else{
                this.shower.setFullEq(this.singleEq + newSingleEq);
            }
        }

        if (this.curState == Statements.ENTER_ANY_NUM ) {
            this.shower.setSingleEq("" + this.finalNumber);
        }else{
            this.shower.setSingleEq(this.singleEq);
        }
    }

    private void applyOP(String op){
        float second = Float.parseFloat(this.singleEq.replaceAll(",", "."));

        this.finalNumber = OperationController.SignToOperation(
                this.operation,
                this.finalNumber, second
        );

        this.curState = Statements.ENTER_ANY_NUM;
        this.operation = op;
    }


    private void processEnterNumber(String number, boolean isEnteredNumberFloat){

        if (this.curState == Statements.ENTER_ANY_NUM){
            this.singleEq = number;
            this.curState = Statements.ENTER_INT_NUMBER;
        }else {
            this.singleEq += number;
        }

        if (isEnteredNumberFloat){
            this.curState = Statements.ENTER_FLOAT_NUMBER;
        }
    }

    private void processBadSign(String badSign){
        // Bad sign, get out of here
        throw new NumberFormatException(String.format("Bad order or input character (%s)", badSign));
    }

    private void processOPInPlace(String op){

        if (curState == Statements.ENTER_ANY_NUM){
            throw new NumberFormatException("Wrong order for sqrt operation");
        }

        String oldValue = this.singleEq;
        // Process written number
        this.singleEq = "" + OperationController.SignToOperation(
                op,
                Float.parseFloat(this.singleEq.replaceAll(",", ".")), Float.NaN
        );

        if (curState == Statements.ENTER_SIGN){
            // Enter second inplace operation
            this.shower.removeLastEntered();
            this.prevInPlaceOP = op + "(" + this.prevInPlaceOP + ")";

        }else {
            // Show result and we want next some operation
            this.curState = Statements.ENTER_SIGN;
            this.prevInPlaceOP = op + "(" + oldValue + ")";
        }

        shower.setFullEq(this.prevInPlaceOP);
        shower.setSingleEq(this.singleEq);

        this.isInPlaceShown = true;
    }

    private void calculateFullResult(){
        if (curState != Statements.ENTER_ANY_NUM) {
            // Apply operation
            this.finalNumber = OperationController.SignToOperation(
                    this.operation,
                    this.finalNumber, Float.parseFloat(this.singleEq.replaceAll(",", "."))
            );

            this.singleEq = "" + this.finalNumber;
            this.shower.resetFullEq();
            this.shower.setFullEq(OperationController.EQUAL);
            this.shower.setSingleEq(this.singleEq);

            this.finalNumber = 0.0f;
            this.operation = OperationController.PLUS;
            this.curState = Statements.ENTER_SIGN;
        }
    }

    private boolean isNumeric(String str) {
        if (str == null){
            return false;
        }

        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void resetAllControls(){
        this.operation = ".";
        this.singleEq = "";

        this.finalNumber = 0.0f;
        this.operation = OperationController.PLUS;

        this.curState = Statements.ENTER_ANY_NUM;

        this.shower.resetFullEq();
    }

    public void clearLastEnter(){
        this.singleEq = "";
        this.shower.setSingleEq(this.singleEq);

        curState = Statements.ENTER_ANY_NUM;
    }
}
