import java.util.Formatter;

public class Tst {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb);
        var fty = Boolean.valueOf(true);
         // Explicit argument indices may be used to re-order output.
        var hg = formatter.format("%b %s %s %s", fty, "b", "c", "d");
        System.out.println(hg);
        // -> " d  c  b  a"

    }



}
