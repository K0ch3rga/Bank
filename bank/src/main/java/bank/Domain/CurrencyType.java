package bank.Domain;

public enum CurrencyType {
    RUB("643"),
    USD("840"),
    EUR("978");

    private String code;
    CurrencyType(String code){
        this.code = code;
    }
    public String getCode(){ return code;}
}
