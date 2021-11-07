public class App {
    public static void main(String[] args) throws Exception {
        Postcode postcode = new Postcode("690000k");
        System.out.println(postcode.isCorrect());

        PassportNumber passportNumber = new PassportNumber("0514fdf304083");
        System.out.println(passportNumber.isCorrect());

        SnilsNumber snilsNumber = new SnilsNumber("123%123-123-00");
        System.out.println(snilsNumber.isCorrect());
    }
}
