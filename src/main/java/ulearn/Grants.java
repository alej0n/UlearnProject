package ulearn;

import java.text.ParseException;

public class Grants {

    private final String nameCompany;
    private final String adress;
    private final Double amount;
    private final int fiscalYear;
    private final String typeBusiness;
    private final int numberJobs;

    public Grants(String[] data) throws ParseException {
        this.nameCompany = data[0];
        this.adress = data[1];
        this.amount = Double.parseDouble(data[2].replaceAll("[^\\d.]", ""));
        this.fiscalYear = Integer.parseInt(data[3]);
        this.typeBusiness = data[4];
        this.numberJobs = Integer.parseInt(data[5]);
    }

    public Grants(String nameCompany,String adress,Double amount,int fiscalYear,
        String typeBusiness,int numberJobs) {
        this.nameCompany = nameCompany;
        this.adress = adress;
        this.amount = amount;
        this.fiscalYear = fiscalYear;
        this.typeBusiness = typeBusiness;
        this.numberJobs = numberJobs;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getAdress() {
        return adress;
    }

    public Double getAmount() {
        return amount;
    }

    public int getFiscalYear() {
        return fiscalYear;
    }

    public String getTypeBusiness() {
        return typeBusiness;
    }

    public int getNumberJobs() {
        return numberJobs;
    }
}
