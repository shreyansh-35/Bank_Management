package interfaces;

public interface ValidationInterface {
    public String doHashing(String password);
    public boolean isValidEmail(String email);
    public boolean isValidAadhar(String aadhar);
    public boolean isValidPhoneNumber(String phoneNumber);
    public boolean isValidPassword(String password);
}
