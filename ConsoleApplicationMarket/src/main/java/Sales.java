import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Sales {
    private static int id;
    public final String no;
    public final List<SalesItem> salesItem;
    public double price;
    public final LocalDate date;

    static {
        id = 1000;
    }

    public Sales(List<SalesItem> salesItem, double price) {
        id++;
        this.no = "" + id;
        this.salesItem = salesItem;
        this.price = price;
//        DateTimeFormatter oldFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        String today = LocalDate.now().format(oldFormatter);
//        LocalDate dateTime = LocalDate.parse(today,oldFormatter);
        this.date = LocalDate.now();
    }
}
