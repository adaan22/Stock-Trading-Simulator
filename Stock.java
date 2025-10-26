import java.io.*;
import java.net.URL;
import java.util.*;
import java.net.HttpURLConnection;


class Stock {

  public static double stockPrice;
  
  public static String searchStock(Scanner stringScanner) throws IOException {

    System.out.println("\nPlease input your stock's code:");
    String stockCode = stringScanner.next();

     URL url = new URL("https://www.marketwatch.com/investing/stock/" + stockCode + "/downloaddatapartial?startdate=11/08/2023%2000:00:00&enddate=12/08/2023%2023:59:59&daterange=d30&frequency=p1d&csvdownload=true&downloadpartial=false&newdates=false");

  try (InputStream linkStream = url.openStream();
       BufferedInputStream bis = new BufferedInputStream(linkStream);
       FileOutputStream fops = new FileOutputStream(stockCode)){

    byte[] d = new byte[2024];
    int i;
    while ((i = bis.read(d, 0, 2024)) != -1) {
      fops.write(d, 0, i);

  }}
    return stockCode;
  }

  public static void removeStock(Scanner intScanner, ArrayList <String> stockList) {
    for (int stockIter = 0; stockIter < stockList.size(); stockIter++) {
      System.out.println(stockIter+1 + ". " + stockList.get(stockIter));
  }
    System.out.println("\nWhich one would you like to  remove?");

    int removedIndex = intScanner.nextInt();
    stockList.remove(removedIndex-1);
    System.out.println("\nRemoved!");
  }

  public static double stockPrice (String ticker) {
    try {
      // API
      //https://api.polygon.io/v3/reference/tickers?ticker=AAPL&active=true&apiKey=Wd0P3QtWO107gpjoOr7Bps9EWW9PATf8

      URL tickerSearch = new URL("https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=cltlqv1r01qlu9okdu2gcltlqv1r01qlu9okdu30");

      HttpURLConnection polygonConnect = (HttpURLConnection) tickerSearch.openConnection();
      polygonConnect.setRequestMethod("GET");
      polygonConnect.connect();

      int polygonResponse = polygonConnect.getResponseCode();

      if (polygonResponse != 200) {
        throw new RuntimeException("HttpResponseCode: " + polygonResponse);
      } else {
        Scanner polygonScanner = new Scanner (tickerSearch.openStream());
        StringBuilder polygonOutput = new StringBuilder();

        while (polygonScanner.hasNext()) {
          polygonOutput.append(polygonScanner.nextLine());
        }

        // System.out.println(polygonOutput);

        int endingIndex = polygonOutput.indexOf(",");
        String priceString = polygonOutput.substring(5, endingIndex);
        stockPrice = Double.parseDouble(priceString);

        // System.out.println("\n" + stockPrice);

  

      }

    } catch (Exception e) {}

    return stockPrice;
  }



}

