public class Scratch {
    public static void main(String[] args) {
        /*
        double purchase = Double.parseDouble(args[0]);
        double amount   = Double.parseDouble(args[1]);
        double change   = amount - purchase;
        StdOut.println(change);

        int x = (int) change;            // integer part...
        int f = (int)((change - x)*100); // fraction part

        // bills
        int twenty = x / 20;
        int ten    = x % 20 / 10;
        int five   = x % 20 % 10 / 5;
        int one    = x % 20 % 10 % 5;

        // coins
        int quarter = f / 25;
        int nickel  = f % 25 / 10;
        int dime    = f % 25 % 10 / 5;
        int pennies = f - 25*quarter - 10*nickel - 5*dime;

        // @WARNING: float arithmetic is only approximations.
        // loss may have occured.

        double loss = change - (x + f/100.0);
        pennies += Math.round(loss*100); */
        int M = 16;
        while (!StdIn.isEmpty()) {
            int hash = StdIn.readInt();
            StdOut.println(hash % M);
        }
    }
}
