package dao;

import dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoObjectCreator;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if (daoObjectCreator==null){
            daoObjectCreator= new DAOFactory();
        }
        return daoObjectCreator;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,BILL,PET,INHOUSE,OPERATION,EMPSCHED,EMPLOYEE,EMPLOYEE_SCHEDULE,VACCINATION,USER,QUERY_DAO
    }

    public <T extends SuperDAO>T getDAO(DAOTypes res) {
        switch (res){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case BILL:
                return (T) new BillDAOImpl();
            case PET:
                return (T) new PetDAOImpl();
            case QUERY_DAO:
                return (T) new QueryDAOImpl();
            case INHOUSE:
                return (T) new InhouseDAOImpl();
            case OPERATION:
                return (T) new OperationDAOImpl();
            case EMPSCHED:
                return (T) new EmpSchedDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case EMPLOYEE_SCHEDULE:
                return (T) new EmployeeScheduleDAOImpl();
            case VACCINATION:
                return (T) new VaccinationDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }


}
