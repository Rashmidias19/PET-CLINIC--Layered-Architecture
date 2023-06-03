package bo;

import bo.impl.*;
import dao.impl.*;

import java.sql.SQLException;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBOFactory() throws SQLException, ClassNotFoundException {
        return boFactory == null ? boFactory= new BOFactory() : boFactory;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,BILL,PET,INHOUSE,OPERATION,EMPLOYEE,EMPLOYEE_SCHEDULE,VACCINATION,USER
    }

    public static <T extends SuperBO>T getBO(BOTypes res) {
        switch (res){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case BILL:
                return (T) new BillBOImpl();
            case PET:
                return (T) new PetBOImpl();
            case INHOUSE:
                return (T) new InhouseBOImpl();
            case OPERATION:
                return (T) new OperationBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case EMPLOYEE_SCHEDULE:
                return (T) new EmployeeScheduleBOImpl();
            case VACCINATION:
                return (T) new VaccinationBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }
}
